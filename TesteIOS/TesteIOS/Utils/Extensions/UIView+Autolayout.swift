//
//  UIView+Autolayout.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

import UIKit

extension UIView {
    @discardableResult
    public func anchor(top: NSLayoutYAxisAnchor, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        topAnchor.constraint(equalTo: top, constant: padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(bottom: NSLayoutYAxisAnchor, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        bottomAnchor.constraint(equalTo: bottom, constant: -padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(leading: NSLayoutXAxisAnchor, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        leadingAnchor.constraint(equalTo: leading, constant: padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(trailing: NSLayoutXAxisAnchor, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        trailingAnchor.constraint(equalTo: trailing, constant: -padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(centerX: NSLayoutXAxisAnchor, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        centerXAnchor.constraint(equalTo: centerX, constant: padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(centerY: NSLayoutYAxisAnchor, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        centerYAnchor.constraint(equalTo: centerY, constant: padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(widthConstant: CGFloat) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        widthAnchor.constraint(equalToConstant: widthConstant).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(heightConstant: CGFloat) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        heightAnchor.constraint(equalToConstant: heightConstant).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(width: NSLayoutDimension, multiplier: CGFloat = 1.0, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        widthAnchor.constraint(equalTo: width, multiplier: multiplier, constant: -padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(height: NSLayoutDimension, multiplier: CGFloat = 1.0, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        heightAnchor.constraint(equalTo: height, multiplier: multiplier, constant: -padding).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(aspectRatio: CGFloat) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        widthAnchor.constraint(equalTo: heightAnchor, multiplier: aspectRatio).isActive = true
        return self
    }
    
    @discardableResult
    public func anchor(widthLessThanOrEqualTo: NSLayoutDimension, multiplier: CGFloat = 1.0, padding: CGFloat = 0) -> Self {
        translatesAutoresizingMaskIntoConstraints = false
        widthAnchor.constraint(lessThanOrEqualTo: widthLessThanOrEqualTo, multiplier: multiplier, constant: -padding).isActive = true
        return self
    }
}
