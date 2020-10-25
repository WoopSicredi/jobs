//
//  PersonCollectionViewCell.swift
//  TesteIOS
//
//  Created by Levy Cristian on 24/10/20.
//

import UIKit

class PersonCollectionViewCell: UICollectionViewCell {
    
    public lazy var pictureImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.backgroundColor = .gray
        imageView.contentMode = .scaleAspectFill
        imageView.clipsToBounds = true
        imageView.layer.cornerRadius = self.frame.width/2
        return imageView
    }()
    
    public lazy var nameLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.font = UIFont.systemFont(ofSize: 22, weight: .semibold)
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
}

extension PersonCollectionViewCell: ViewCode {
    func buildViewHierarchy() {
        addSubview(pictureImageView)
        addSubview(nameLabel)
    }
    
    func setupConstraints() {
        pictureImageView
            .anchor(top: topAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(width: widthAnchor)
            .anchor(height: widthAnchor)
        
        nameLabel
            .anchor(top: pictureImageView.bottomAnchor, padding: 2)
            .anchor(centerX: pictureImageView.centerXAnchor)
            .anchor(width: widthAnchor)
            .anchor(bottom: bottomAnchor, padding: 12)
    }
    
}
