//
//  CheckinButton.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import UIKit

class CheckinButton: UIButton {
    
    var title: String? {
        willSet(newValue) {
            setTitle(newValue, for: .normal)
        }
    }
    
    override var isHighlighted: Bool {
        didSet {
            backgroundColor = isHighlighted ? .actionDarkColor : .actionColor
        }
    }
    
    // MARK: - Initializer & functions
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupButton()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupButton()
    }
    
    private func setupButton() {
        titleLabel?.font = UIFont.systemFont(ofSize: 17, weight: .semibold)
        clipsToBounds = true
        layer.cornerRadius = 8
        layer.borderWidth = 0
        contentHorizontalAlignment = .center
        setTitleColor(UIColor.black, for: .normal)
        backgroundColor = .actionColor
    }
}

