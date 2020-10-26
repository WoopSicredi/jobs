//
//  PeoplesTableViewCell.swift
//  TesteIOS
//
//  Created by Levy Cristian on 24/10/20.
//

import UIKit

class PeopleTableViewCell: UITableViewCell {
    
    public lazy var collectionView: UICollectionView = {
        let flowLayout = UICollectionViewFlowLayout()
        flowLayout.scrollDirection = .horizontal
        flowLayout.itemSize = CGSize(width: UIScreen.main.bounds.width/3 - 6, height: 180)
        flowLayout.minimumLineSpacing = 12
        let collectionView = UICollectionView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 180), collectionViewLayout: flowLayout)
        collectionView.alwaysBounceHorizontal = true
        collectionView.register(PersonCollectionViewCell.self, forCellWithReuseIdentifier: PersonCollectionViewCell.reuseIdentifier)
        return collectionView
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupView()
    }
}

extension PeopleTableViewCell: ViewCode {
    func buildViewHierarchy() {
        contentView.addSubview(collectionView)
    }
    
    func setupConstraints() {
        if let superView = collectionView.superview {
            collectionView
                .anchor(top: superView.topAnchor)
                .anchor(leading: superView.leadingAnchor)
                .anchor(trailing: superView.trailingAnchor)
                .anchor(bottom: superView.bottomAnchor)
        }
    }
    
    func setupAdditionalConfigurantion() {
        backgroundColor = .white
        collectionView.backgroundColor = .white
    }
}
