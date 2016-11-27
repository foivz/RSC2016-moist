//
//  AppDelegate.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 26/11/2016.
//  Copyright © 2016 Infinum Ltd. All rights reserved.
//

import UIKit
import FBSDKCoreKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {

        let navigationController = UINavigationController()
        let wireframe = LoginWireframe(navigationController: navigationController)
        wireframe.show(with: .push, animated: true)

        window?.rootViewController = wireframe.navigationController

        FBSDKApplicationDelegate.sharedInstance().application(application, didFinishLaunchingWithOptions: launchOptions)
        return true
    }

    func application(_ app: UIApplication, open url: URL, options: [UIApplicationOpenURLOptionsKey : Any] = [:]) -> Bool {
        
        let handled = FBSDKApplicationDelegate.sharedInstance()
            .application(
                app,
                open: url,
                sourceApplication: options[UIApplicationOpenURLOptionsKey.sourceApplication] as! String!,
                annotation: options[UIApplicationOpenURLOptionsKey.annotation])
        
        return handled
    }
}

