//
//  Person.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

// MARK: - Person
struct Person: Codable {
    let picture: String
    let name, eventID, id: String

    enum CodingKeys: String, CodingKey {
        case picture, name
        case eventID = "eventId"
        case id
    }
}
