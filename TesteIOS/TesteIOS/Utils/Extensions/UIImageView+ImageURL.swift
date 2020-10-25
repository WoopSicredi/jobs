//
//  UIImageView+ImageURL.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import UIKit

extension UIImageView {
    func downloadImageFrom(url: String) {
        DispatchQueue.global(qos: .background).async { [weak self] in
            if let imageURL = URL(string: url) {
                if let data = try? Data(contentsOf: imageURL) {
                    if let image = UIImage(data: data) {
                        DispatchQueue.main.async {
                            self?.image = image
                        }
                    } else {
                        self?.image = UIImage(named: "errorImage")
                    }
                } else {
                    DispatchQueue.main.async {
                        self?.image = UIImage(named: "errorImage")
                    }
                }
            } else {
                DispatchQueue.main.async {
                    self?.image = UIImage(named: "errorImage")
                }
            }
        }
    }
}
