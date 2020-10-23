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
    
    public var imageURL: String {
        return event.image
    }
    
    public var price: String {
        return "R$ \(event.price)"
    }
    
    public init(event: Event) {
        self.event = event
    }
    
    public func downloadImagefromEventURL(completion: @escaping (Result<Data, Error>) -> Void) {
        guard let imageURL = URL(string: imageURL) else {
            return
        }
        
        let session = URLSession(configuration: .default)
        let downloadPicTask = session.dataTask(with: imageURL) { (data, response, error) in
            if let e = error {
                completion(.failure(e))
            } else {
                if ((response as? HTTPURLResponse) != nil) {
                    if let imageData = data {
                        completion(.success(imageData))
                    } else {
                        print("Couldn't get image: Image is nil")
                    }
                } else {
                    print("Couldn't get response code for some reason")
                }
            }
        }
        downloadPicTask.resume()
    }
}
