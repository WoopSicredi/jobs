//
//  CheckinResult.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

struct CheckinResult: Codable {
    let code: String
    
    enum CodingKeys: String, CodingKey {
        case code
    }
}

typealias RequestResult = [Event]
