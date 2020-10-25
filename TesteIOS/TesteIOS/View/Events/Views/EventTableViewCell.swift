//
//  EventTableViewCell.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class EventTableViewCell: UITableViewCell {
    
    public lazy var banerImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.backgroundColor = .gray
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        return imageView
    }()
    
    public lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.font = UIFont.systemFont(ofSize: 17, weight: .semibold)
        return label
    }()
    
    public lazy var priceLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        return label
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupView()
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.banerImageView.image = nil
        self.titleLabel.text = nil
        self.priceLabel.text = nil
    }
    
    deinit {
        self.banerImageView.image = nil
        self.titleLabel.text = nil
        self.priceLabel.text = nil
    }
}

extension EventTableViewCell: ViewCode {
    func buildViewHierarchy() {
        addSubview(banerImageView)
        addSubview(titleLabel)
        addSubview(priceLabel)
    }
    
    func setupConstraints() {
        banerImageView
            .anchor(top: topAnchor, padding: 12)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(heightConstant: 240)
        
        titleLabel
            .anchor(top: banerImageView.bottomAnchor, padding: 12)
            .anchor(leading: leadingAnchor, padding: 12)
            .anchor(bottom: bottomAnchor, padding: 12)
            .anchor(width: widthAnchor, multiplier: 0.6)
        
        priceLabel
            .anchor(top: banerImageView.bottomAnchor, padding: 12)
            .anchor(leading: titleLabel.trailingAnchor, padding: 12)
            .anchor(trailing: trailingAnchor, padding: 12)
            .anchor(bottom: bottomAnchor, padding: 12)
    }
}
