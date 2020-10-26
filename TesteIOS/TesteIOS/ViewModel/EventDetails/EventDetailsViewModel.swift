//
//  EventDetailsViewModel.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import Foundation

class EventDetailsViewModel {
    // MARK: - Typealias
    public typealias BooleanClosure = ((Bool) -> Void)
    public typealias EventClosure = (Event) -> Void
    public typealias ErrorClousure = ((APIError) -> Void)
    public typealias StringClosure = (String) -> Void

    // MARK: - Binding closures
    public var errorLoadingDataClosure: ErrorClousure?
    public var reloadViewClosure: EventClosure?
    public var isLoadingClosure: BooleanClosure?
    public var checkInCode: StringClosure?
    
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
        isLoadingClosure?(true)
        EventClient().getEventBy(eventID: eventID) { [weak self] result in
            switch result {
            case .success(let event):
                self?.isLoadingClosure?(false)
                guard let event = event else {
                    return
                }
                self?.event = event
            case .failure(let error):
                self?.isLoadingClosure?(false)
                self?.errorLoadingDataClosure?(error)
            }
        }
    }
    
    public func checkIn(eventID: String, user: String, email: String) {
        isLoadingClosure?(true)
        EventClient().checkinToEvent(eventID: eventID,
                                     userName: user,
                                     userEmail: email) { [weak self] result in
            switch result {
            case .success(let result):
                self?.isLoadingClosure?(false)
                guard let code = result?.code else {
                    return
                }
                self?.checkInCode?(code)
            case .failure(let error):
                self?.isLoadingClosure?(false)
                self?.errorLoadingDataClosure?(error)
            }
        }
    }
}
