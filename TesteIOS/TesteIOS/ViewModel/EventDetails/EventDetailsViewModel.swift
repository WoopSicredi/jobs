//
//  EventDetailsViewModel.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import Foundation

class EventDetailsViewModel {
    // MARK: - Typealias
    public typealias EventClosure = (Event) -> Void
    public typealias ErrorClousure = ((APIError) -> Void)

    // MARK: - Binding closures
    public var errorLoadingDataClosure: ErrorClousure?
    public var reloadViewClosure: EventClosure?
    
    // MARK: - Control Variables
    private var event: Event? {
        willSet(newValue) {
            guard let event = newValue else {
                return
            }
            personViewModels = event.people.map({ PersonViewModel(person: $0) })
            reloadViewClosure?(event)
        }
    }
    
    public var personViewModels: [PersonViewModel] = []
    
    public init(eventID: String?) {
        guard let id = eventID else {
            return
        }
        self.getEventDetails(eventID: id)
    }
    
    public func getEventDetails(eventID: String) {
        EventClient().getEventBy(eventID: eventID) { [weak self] result in
            switch result {
            case .success(let event):
                guard let event = event else {
                    return
                }
                self?.event = event
            case .failure(let error):
                self?.errorLoadingDataClosure?(error)
            }
        }
    }
}
