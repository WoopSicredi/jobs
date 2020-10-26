//
//  Identifiable.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

public protocol Identifiable {
    static var reuseIdentifier: String { get }
}

extension Identifiable {
    public static var reuseIdentifier: String {
        return String(describing: Self.self)
    }
}

extension UITableViewCell: Identifiable {}
extension UICollectionViewCell: Identifiable {}
