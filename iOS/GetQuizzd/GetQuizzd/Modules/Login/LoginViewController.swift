//
//  LoginViewController.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 26/11/2016.
//  Copyright (c) 2016 Infinum Ltd. All rights reserved.
//
//  This file was generated by the 🐍 VIPER generator
//

import UIKit

final class LoginViewController: UIViewController {

    var presenter: LoginPresenterInterface!

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func btnFbLoginActionHandler() {
        presenter.didPressLogin(type: .Facebook)        
    }
}

// MARK: - Extensions -

extension LoginViewController: LoginViewInterface {
    func showMessage(message: String) {
        let alertView = UIAlertController(title: "GetQuizzd", message: message, preferredStyle: UIAlertControllerStyle.alert)
        alertView.addAction(UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: nil))
        self.present(alertView, animated: true, completion: nil)
    }
}