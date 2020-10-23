//
//  Descriptable.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

protocol Descriptable {
    
    var description: String { get }
    
}

protocol ErrorDescriptable: Descriptable {}

