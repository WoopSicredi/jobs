//
//  URLRequest+Extensions.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

extension URLRequest {
    
    mutating func setJSONContentType() {
        setValue("application/json; charset=utf-8",
                 forHTTPHeaderField: "Content-Type")
    }
    
}
