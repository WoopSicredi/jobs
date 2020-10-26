//
//  LoadingView.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import UIKit

class LoadingView: UIView {
    
    // MARK: - UI Variables
    private lazy var containerView: UIView = {
        let view = UIView()
        view.backgroundColor = UIColor.black.withAlphaComponent(0.7)
        view.layer.cornerRadius = 16
        return view
    }()
    
    lazy var activyIndicator: UIActivityIndicatorView = {
        let indicator = UIActivityIndicatorView()
        indicator.style = .whiteLarge
        indicator.color = .white
        return indicator
    }()
    
    private lazy var loadingLabel: UILabel = {
        let label = UILabel()
        label.text = "Carregando..."
        label.font = UIFont.systemFont(ofSize: 17, weight: .semibold)
        label.textColor = .white
        label.textAlignment = .center
        return label
    }()
    
    // MARK: - Initializer & functions
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupView()
        activyIndicator.startAnimating()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupView()
    }
}

extension LoadingView: ViewCode {
    func buildViewHierarchy() {
        addSubview(containerView)
        containerView.addSubview(activyIndicator)
        containerView.addSubview(loadingLabel)
    }
    
    func setupConstraints() {
        containerView
            .anchor(centerX: centerXAnchor)
            .anchor(centerY: centerYAnchor)
            .anchor(height: heightAnchor, multiplier: 0.25)
            .anchor(widthConstant: 200)
        
        activyIndicator
            .anchor(centerX: containerView.centerXAnchor)
            .anchor(centerY: containerView.centerYAnchor)
            .anchor(height: widthAnchor, multiplier: 0.05)
            .anchor(width: widthAnchor, multiplier: 0.05)
        
        loadingLabel
            .anchor(top: activyIndicator.bottomAnchor)
            .anchor(leading: containerView.leadingAnchor)
            .anchor(trailing: containerView.trailingAnchor)
            .anchor(bottom: containerView.bottomAnchor, padding: 6)
    }
    
}

