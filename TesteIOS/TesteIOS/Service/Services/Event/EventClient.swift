//
//  EventClient.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

class EventClient: APIClient {
    
    var session: URLSession
    
    init(configuration: URLSessionConfiguration) {
        configuration.requestCachePolicy = .reloadIgnoringLocalCacheData
        self.session = URLSession(configuration: configuration)
    }
    
    convenience init() {
        self.init(configuration: .default)
    }
    // MARK: - Custom List Details
    
    func getAllEvents(completion: @escaping (Result<RequestResult?, APIError>) -> Void) {
        let request = EventProvider.getAll.request
        fetch(with: request, decode: { json -> RequestResult? in
            guard let list = json as? RequestResult else { return  nil }
            return list
        }, completion: completion)
    }
    
    func getEventBy(eventID: String, completion: @escaping (Result<Event?, APIError>) -> Void) {
        let request = EventProvider.getDetailBy(eventID: eventID).request
        fetch(with: request, decode: { json -> Event? in
            guard let event = json as? Event else { return  nil }
            return event
        }, completion: completion)
    }
    
    func checkinToEvent(eventID: String,
                        userName: String,
                        userEmail: String,
                        completion: @escaping (Result<CheckinResult?, APIError>) -> Void) {
        let request = EventProvider.checkIn(eventID: eventID, userName: userName, userEmail: userEmail).request
        fetch(with: request, decode: { json -> CheckinResult? in
            guard let result = json as? CheckinResult else { return  nil }
            return result
        }, completion: completion)
    }
}

