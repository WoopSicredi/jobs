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
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: flowLayout)
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
        addSubview(collectionView)
    }
    
    func setupConstraints() {
        collectionView
            .anchor(top: topAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(bottom: safeAreaLayoutGuide.bottomAnchor)
    }
    func setupAdditionalConfigurantion() {
        backgroundColor = .white
        collectionView.backgroundColor = .white
    }
}
