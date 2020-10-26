//
//  EventsView.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class EventsView: UIView {
    
    var isLoading: Bool = false {
        willSet(newValue) {
            if newValue {
                loadingView.isHidden = false
                loadingView.activyIndicator.startAnimating()
            } else {
                loadingView.isHidden = true
                loadingView.activyIndicator.stopAnimating()
            }
        }
    }
    
    lazy var tableView: UITableView = {
        let tableView = UITableView()
        tableView.register(EventTableViewCell.self, forCellReuseIdentifier: EventTableViewCell.reuseIdentifier)
        tableView.tableFooterView = UIView()
        return tableView
    }()
    
    private lazy var loadingView: LoadingView = {
        let loadingView = LoadingView()
        return loadingView
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

extension EventsView: ViewCode {
    func buildViewHierarchy() {
        addSubview(tableView)
        addSubview(loadingView)
    }
    
    func setupConstraints() {
        tableView
            .anchor(top: topAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(bottom: safeAreaLayoutGuide.bottomAnchor)
        
        loadingView
            .anchor(top: topAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(bottom: bottomAnchor)
    }
    
    func setupAdditionalConfigurantion() {
        backgroundColor = .backgroundColor
    }
}
