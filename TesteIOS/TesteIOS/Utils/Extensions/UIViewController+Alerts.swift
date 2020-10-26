//
//  UIViewController+Alerts.swift
//  TesteIOS
//
//  Created by Levy Cristian on 25/10/20.
//

import UIKit

extension UIViewController {
    func showAlert(_ title: String?, message: String?, button: String?, handler: ((UIAlertAction) -> Void)?) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: button, style: .default, handler: handler))
        present(alert, animated: true, completion: nil)
    }
}
