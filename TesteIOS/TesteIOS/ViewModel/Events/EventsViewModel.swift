//
//  EventsViewModel.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

class EventsViewModel {
    // MARK: - Typealias
    public typealias BindingClosure = () -> Void
    public typealias ErrorClousure = ((APIError) -> Void)

    // MARK: - Binding closures
    public var errorLoadingDataClosure: ErrorClousure?
    public var reloadTableViewClosure: BindingClosure?
    
    // MARK: - Control Variables
    private var events: [Event] {
        willSet(newValue) {
            eventCellViewModels = newValue.map({ EventCellViewModel(event: $0) })
        }
    }
    
    public var eventCellViewModels: [EventCellViewModel] = [] {
        didSet {
            reloadTableViewClosure?()
        }
    }
    
    public init(events: [Event] = [Event]()) {
        self.events = events
    }
    
    public func getAllEvents() {
        EventClient().getAllEvents { [weak self] (result) in
            switch result {
            case .success(let result):
                guard let result = result else {
                    return
                }
                self?.events = result
            case .failure(let erro):
                self?.errorLoadingDataClosure?(erro)
            }
        }
    }
}
