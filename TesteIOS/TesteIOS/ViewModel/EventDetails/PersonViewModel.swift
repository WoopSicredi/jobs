//
//  PersonViewModel.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import Foundation

class PersonViewModel {
    // MARK: - Variales
    private var person: Person
    
    public var name: String {
        return person.name
    }
    
    public var prictureURL: String {
        return person.picture
    }
    
    public init(person: Person) {
        self.person = person
    }
}
