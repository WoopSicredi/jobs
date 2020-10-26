//
//  EventCellViewModel.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

class EventCellViewModel {
    
    // MARK: - Variales
    private var event: Event
    
    public var title: String {
        return event.title
    }
    
    public var eventID: String {
        return event.id
    }
    
    public var imageURL: String {
        return event.image
    }
    
    public var price: String {
        return "R$ \(event.price)"
    }
    
    public init(event: Event) {
        self.event = event
    }
}
