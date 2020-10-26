//
//  EventDetailsView.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class EventDetailsView: UIView {
    
    lazy var tapGesture: UITapGestureRecognizer = {
        let tap = UITapGestureRecognizer(target: self, action: #selector(didTapOnDescription(_:)))
        tap.numberOfTouchesRequired = 1
        return tap
    }()
    
    lazy var tableHeaderView: TableHeaderView = {
        let view = TableHeaderView()
        view.descriptionLabel.addGestureRecognizer(tapGesture)
        return view
    }()
    
    lazy var tableView: UITableView = {
        let tableView = UITableView()
        tableView.tableFooterView = UIView()
        tableView.register(PeopleTableViewCell.self, forCellReuseIdentifier: PeopleTableViewCell.reuseIdentifier)
        return tableView
    }()
    
    lazy var toolBar: CustomToolBar = {
        let toolBar = CustomToolBar()
        return toolBar
    }()
    
    func sizeHeaderToFit() {
        tableView.tableHeaderView = tableHeaderView
        
        
        if let headerView = tableView.tableHeaderView {
            headerView.setNeedsLayout()
            headerView.layoutIfNeeded()
                        
            let height = headerView.systemLayoutSizeFitting(UIView.layoutFittingCompressedSize).height
            var headerFrame = headerView.frame

            //Comparison necessary to avoid infinite loop
            if height != headerFrame.size.height {
                headerFrame.size.height = height
                headerView.frame = headerFrame
                tableView.tableHeaderView = headerView
            }
        }
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupView()
    }
    
    @objc private func didTapOnDescription(_ tap: UITapGestureRecognizer) {
        tableHeaderView.descriptionLabel.numberOfLines = tableHeaderView.descriptionLabel.numberOfLines == 0 ? 2 : 0
        UIView.animate(withDuration: 0.1231) { [weak self] in
            self?.sizeHeaderToFit()
            self?.layoutIfNeeded()
        }
    }
}

extension EventDetailsView: ViewCode {
    func buildViewHierarchy() {
        addSubview(tableView)
        addSubview(toolBar)
    }
    
    func setupConstraints() {
        tableView
            .anchor(top: topAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
        
        toolBar
            .anchor(top: tableView.bottomAnchor)
            .anchor(leading: leadingAnchor)
            .anchor(trailing: trailingAnchor)
            .anchor(bottom: safeAreaLayoutGuide.bottomAnchor)
            .anchor(heightConstant: 62)
    }
    
    func setupAdditionalConfigurantion() {
        backgroundColor = .white
    }
}
