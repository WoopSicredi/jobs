//
//  TableHeaderView.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class TableHeaderView: UIView {

    public lazy var banerImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.backgroundColor = .gray
        imageView.contentMode = .scaleAspectFill
        //imageView.image = UIImage(named: "errorImage")
        imageView.clipsToBounds = true
        return imageView
    }()
    
    lazy var contentView: UIView = {
        let content = UIView()
        return content
    }()
    
    public lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.text = "caralho"
        label.lineBreakMode = .byWordWrapping
        label.font = UIFont.systemFont(ofSize: 22, weight: .semibold)
        return label
    }()
    
    public lazy var priceLabel: UILabel = {
        let label = UILabel()
        label.text = "caralho"
        label.textAlignment = .center
        return label
    }()
    
    public lazy var descriptionLabel: UILabel = {
        let label = UILabel()
        label.isUserInteractionEnabled = true
        label.numberOfLines = 2
        label.text = "Forcing navigation to a specific view as a response to an external event has always been a common action in iOS development. Deep links to our app, taps on push notifications, Home Screen quick actions, in-app modals triggered by our backend are common examples that showcase the need to land on a specific screen in our app."
        label.font = UIFont.systemFont(ofSize: 17, weight: .regular)
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupView()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        titleLabel.preferredMaxLayoutWidth = titleLabel.bounds.width
        priceLabel.preferredMaxLayoutWidth = priceLabel.bounds.width
        descriptionLabel.preferredMaxLayoutWidth = descriptionLabel.bounds.width
    }
    
}

extension TableHeaderView: ViewCode {
    func buildViewHierarchy() {
        addSubview(banerImageView)
        addSubview(titleLabel)
        addSubview(priceLabel)
        addSubview(descriptionLabel)
        
    }
    
    func setupConstraints() {
        banerImageView
            .anchor(top: topAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(heightConstant: 480)
        
        titleLabel
            .anchor(top: banerImageView.bottomAnchor, padding: 12)
            .anchor(leading: safeAreaLayoutGuide.leadingAnchor, padding: 12)
            .anchor(width: widthAnchor, multiplier: 0.6)

        priceLabel
            .anchor(top: banerImageView.bottomAnchor, padding: 12)
            .anchor(leading: titleLabel.trailingAnchor, padding: 12)
            .anchor(trailing: safeAreaLayoutGuide.trailingAnchor, padding: 12)

        descriptionLabel
            .anchor(top: titleLabel.bottomAnchor, padding: 12)
            .anchor(leading: safeAreaLayoutGuide.leadingAnchor, padding: 12)
            .anchor(trailing: safeAreaLayoutGuide.trailingAnchor, padding: 12)
            .anchor(bottom: bottomAnchor, padding: 12)
    }
}
