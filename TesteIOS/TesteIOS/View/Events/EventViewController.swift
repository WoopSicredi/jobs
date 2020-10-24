//
//  EventViewController.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class EventViewController: UIViewController {
    
    private lazy var eventView: EventsView = {
        let view = EventsView()
        view.tableView.delegate = self
        view.tableView.dataSource = self
        return view
    }()
    
    private lazy var viewModel: EventsViewModel = {
        let viewModel = EventsViewModel()
        return viewModel
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.prefersLargeTitles = true
        self.view = eventView
                creatingBuinds()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        configureController()
        viewModel.getAllEvents()
    }
    
    private func configureController() {
        navigationController?.navigationItem.largeTitleDisplayMode = .always
    }
    
    private func creatingBuinds() {
        viewModel.reloadTableViewClosure = { [weak self] in
            self?.eventView.tableView.reloadData()
        }
    }
}

extension EventViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.eventCellViewModels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellViewModel = viewModel.eventCellViewModels[indexPath.row]
        guard let cell = tableView.dequeueReusableCell(withIdentifier: EventTableViewCell.reuseIdentifier, for: indexPath) as? EventTableViewCell else {
            return UITableViewCell()
        }
        cell.titleLabel.text = cellViewModel.title
        cell.priceLabel.text = cellViewModel.price
        DispatchQueue.global(qos: .background).async {
            cellViewModel.downloadImagefromEventURL { result in
                switch result {
                case .success(let data):
                    DispatchQueue.main.async {
                        cell.banerImageView.image = UIImage(data: data)
                    }
                case .failure:
                    DispatchQueue.main.async {
                        cell.banerImageView.image = UIImage(named: "errorImage")
                    }
                }
            }
        }
        return cell
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let selectedeEventID = viewModel.eventCellViewModels[indexPath.row].eventID
        let controller = EventDetailsViewController()
        self.navigationController?.pushViewController(controller, animated: true)
    }
}
