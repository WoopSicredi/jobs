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
        label.lineBreakMode = .byWordWrapping
        label.font = UIFont.systemFont(ofSize: 22, weight: .semibold)
        return label
    }()
    
    public lazy var priceLabel: UILabel = {
        let label = UILabel()
        let size: CGFloat = 80
        label.textAlignment = .center
        label.font = UIFont.systemFont(ofSize: 17)
        label.frame = CGRect(x : 50.0, y : 50.0, width : size, height :  size)
        label.layer.cornerRadius = size / 2
        label.layer.borderWidth = 3.0
        label.textColor = .white
        label.layer.backgroundColor = UIColor.actionColor.cgColor
        label.layer.borderColor = UIColor.actionColor.cgColor

        return label
    }()
    
    public lazy var descriptionLabel: UILabel = {
        let label = UILabel()
        label.isUserInteractionEnabled = true
        label.numberOfLines = 2
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
            .anchor(top: banerImageView.bottomAnchor, padding: -80/2)
            .anchor(trailing: trailingAnchor, padding: 24)
            .anchor(heightConstant: 80)
            .anchor(widthConstant: 80)
            
        descriptionLabel
            .anchor(top: titleLabel.bottomAnchor, padding: 12)
            .anchor(leading: safeAreaLayoutGuide.leadingAnchor, padding: 12)
            .anchor(trailing: safeAreaLayoutGuide.trailingAnchor, padding: 12)
            .anchor(bottom: bottomAnchor, padding: 24)
    }
}
