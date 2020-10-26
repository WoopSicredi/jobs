//
//  EventDetailsViewController.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class EventDetailsViewController: UIViewController {
    
    var eventID: String?
    
    lazy var viewModel: EventDetailsViewModel = {
        let viewModel = EventDetailsViewModel(eventID: self.eventID)
        return viewModel
    }()

    lazy var eventDetailsView: EventDetailsView = {
        let view = EventDetailsView()
        view.tableView.delegate = self
        view.tableView.dataSource = self
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view = eventDetailsView
        creatingBuinds()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        configureController()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        eventDetailsView.sizeHeaderToFit()
    }
    
    private func configureController() {
        self.navigationItem.largeTitleDisplayMode = .never
    }
    
    private func creatingBuinds() {
        viewModel.reloadViewClosure = { [weak self] event in
            let header = self?.eventDetailsView.tableHeaderView
            header?.banerImageView.downloadImageFrom(url: event.image)
            header?.titleLabel.text = event.title
            header?.priceLabel.text = "R$\(event.price)"
            header?.descriptionLabel.text = event.description
            
            self?.eventDetailsView.tableView.reloadData()
        }
    }

}

extension EventDetailsViewController: UITableViewDataSource, UITableViewDelegate {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.row == 0 {
            guard let cell = tableView.dequeueReusableCell(withIdentifier: PeopleTableViewCell.reuseIdentifier, for: indexPath) as?  PeopleTableViewCell else {
                return UITableViewCell()
            }
            cell.collectionView.delegate = self
            cell.collectionView.dataSource = self
            cell.collectionView.reloadData()
            return cell
        } else {
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.row == 0 {
            return 192
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if section == 0 {
            return "Participantes"
        } else {
            return nil
        }
    }
}

extension EventDetailsViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.personViewModels.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: PersonCollectionViewCell.reuseIdentifier, for: indexPath) as? PersonCollectionViewCell else {
            return UICollectionViewCell()
        }
        let cellViewModel = viewModel.personViewModels[indexPath.row]
        cell.nameLabel.text = cellViewModel.name
        cell.pictureImageView.downloadImageFrom(url: cellViewModel.prictureURL)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 12, left: 12, bottom: 0, right: 12)
    }
    
}

