//
//  CustomToolBar.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import UIKit

class CustomToolBar: UIToolbar {

    lazy var checkinButton: CheckinButton = {
        let button = CheckinButton()
        button.title = "Check In"
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupView()
    }
}

extension CustomToolBar: ViewCode {
    func buildViewHierarchy() {
        addSubview(checkinButton)
    }
    
    func setupConstraints() {
        checkinButton
            .anchor(top: topAnchor, padding: 12)
            .anchor(leading: leadingAnchor, padding: 12)
            .anchor(trailing: trailingAnchor, padding: 12)
            .anchor(bottom: bottomAnchor, padding: 12)
    }
}
