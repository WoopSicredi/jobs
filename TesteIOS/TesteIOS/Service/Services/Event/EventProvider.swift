//
//  EventProvider.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

enum EventProvider {
    
    case getAll
    case getDetailBy(eventID: String)
    case checkIn(eventID: String, userName: String, userEmail: String)
    
}

extension EventProvider: Endpoint {
    var base: String {
        return "http://5f5a8f24d44d640016169133.mockapi.io/api"
    }
    
    var path: String {
        switch self {
        case .getAll:
            return "/events"
        case .getDetailBy(let eventID):
            return "/events/\(eventID)"
        case .checkIn:
            return "/checkin"
        }
    }
    
    var params: [String : Any]? {
        switch self {
        case .getAll, .getDetailBy:
            return nil
        case .checkIn(let eventID, let userName, let userEmail):
            let bodyParams: [String: Any] = ["eventId": eventID,
                                             "name": userName,
                                             "email": userEmail]
            return ["body": bodyParams]
        }
    }
    
    var parameterEncoding: ParameterEnconding {
        switch self {
        case .getAll, .getDetailBy:
            return .defaultEncoding
        case .checkIn:
            return .compositeEncoding
        }
    }
    
    var method: HTTPMethod {
        switch self {
        case .getAll, .getDetailBy:
            return .get
        case .checkIn:
            return .post
        }
    }
}
