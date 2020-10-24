//
//  EventDetailsViewController.swift
//  TesteIOS
//
//  Created by Levy Cristian on 23/10/20.
//

import UIKit

class EventDetailsViewController: UIViewController {

    lazy var eventDetailsView: EventDetailsView = {
        let view = EventDetailsView()
        view.tableView.delegate = self
        view.tableView.dataSource = self
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view = eventDetailsView
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        configureController()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        eventDetailsView.sizeHeaderToFit()
    }
    
    private func configureController() {
        navigationItem.largeTitleDisplayMode = .never
    }

}

extension EventDetailsViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        return UITableViewCell()
    }
}
