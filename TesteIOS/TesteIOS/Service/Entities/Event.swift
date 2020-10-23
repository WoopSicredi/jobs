//
//  Event.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation
// MARK: - ResultElement
struct Event: Codable {
    let people: [Person]
    let date: Int
    let description: String
    let image: String
    let longitude, latitude, price: Double
    let title, id: String

    enum CodingKeys: String, CodingKey {
        case image, longitude, latitude, price, title, id, people, date, description
    }
    
}
