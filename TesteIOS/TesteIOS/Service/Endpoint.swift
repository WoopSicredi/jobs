//
//  Endpoint.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

protocol Endpoint {
    
    var base: String { get }
    var path: String { get }
    var params: [String: Any]? { get }
    var parameterEncoding: ParameterEnconding { get }
    var method: HTTPMethod { get }
    
}

extension Endpoint {
    
    var urlComponents: URLComponents {
        var components = URLComponents(string: base)!
        components.path = path
        return components
    }
    
    var request: URLRequest {
        let url = urlComponents.url!
        var request = URLRequest(url: url)
        request.httpMethod = method.rawValue
        
        
        guard let params = params, method != .get else { return request }
        
        switch parameterEncoding {
        case .defaultEncoding:
            request.httpBody = params.percentEscaped().data(using: .utf8)
        case .jsonEncoding:
            request.setJSONContentType()
            let jsonData = try? JSONSerialization.data(withJSONObject: params)
            request.httpBody = jsonData
        case .compositeEncoding:
            if let bodyParams = params["body"] as? [String: Any] {
                request.setJSONContentType()
                let jsonData = try? JSONSerialization.data(withJSONObject: bodyParams)
                request.httpBody = jsonData
            }
        }
        
        return request
    }
    
}

enum HTTPMethod: String {
    case get = "GET"
    case post = "POST"
}

enum ParameterEnconding {
    case defaultEncoding
    case jsonEncoding
    case compositeEncoding
}

