//
//  ViewCode.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import Foundation

public protocol ViewCode {
    func buildViewHierarchy()
    func setupConstraints()
    func setupAdditionalConfigurantion()
    func setupView()
}

extension ViewCode {
   public func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfigurantion()
    }
    
    public func setupAdditionalConfigurantion() {
        //
    }
}
