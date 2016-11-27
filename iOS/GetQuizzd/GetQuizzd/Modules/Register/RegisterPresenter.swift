//
//  RegisterPresenter.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 26/11/2016.
//  Copyright (c) 2016 Infinum Ltd. All rights reserved.
//
//  This file was generated by the 🐍 VIPER generator
//

import UIKit
import ObjectMapper

final class RegisterPresenter {

    // MARK: - Private properties -

    fileprivate weak var _view: RegisterViewInterface?
    fileprivate var _interactor: RegisterInteractorInterface
    fileprivate var _wireframe: RegisterWireframeInterface

    var user: User

    // MARK: - Lifecycle -

    init (wireframe: RegisterWireframeInterface, view: RegisterViewInterface, interactor: RegisterInteractorInterface) {
        _wireframe = wireframe
        _view = view
        _interactor = interactor
        user = User()
    }
}

// MARK: - Extensions -

extension RegisterPresenter: RegisterPresenterInterface {
    func didPressSignUp(nickname: String) {
        user.nickname = nickname
        
        let params = ["request": ["user" : [user]]]

        let jsonData = try! JSONSerialization.data(withJSONObject: params, options: JSONSerialization.WritingOptions.prettyPrinted)
        
        let jsonString = NSString(data: jsonData, encoding: String.Encoding.utf8.rawValue)! as String

        print(jsonString)
        
        ApiManager.sendRequest("users/create", .post, parameters: params as [String : NSDictionary]!, completion: {
            data in
            
            print(data)
            
        }, error: {
            _ in
            
        }, failure: {
            _ in
            
        })
    }
}
