//
//  ApiManager.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 26/11/2016.
//  Copyright © 2016 Infinum Ltd. All rights reserved.
//

import Foundation
import FacebookLogin
import FBSDKCoreKit
import Alamofire
import SwiftyJSON

public enum RequestType {
    case FacebookLogin
    case GoogleLogin
    case TwitterLogin
}

public class ApiManager {
    
    static let baseApiAddress = "http://138.68.85.154:8000/api/"
    
    public class func sendRequest(requestType: RequestType, view: UIViewController, completion: @escaping (_ result: String) -> Void, failure: @escaping (_ error: String) -> Void) {
        switch requestType {
        case .FacebookLogin:
            sendFacebookLoginRequest(view: view, completion: {
                result in
                
                completion(result)
            }, failure: {
                error in
                
                failure(error)
            })
        case .GoogleLogin:
            sendGoogleLoginRequest()
        case .TwitterLogin:
            sendTwitterLoginRequest()
        }
    }
    
    private class func sendFacebookLoginRequest(view: UIViewController, completion: @escaping (_ result: String) -> Void, failure: @escaping (_ error: String) -> Void) {
        let loginManager = LoginManager()
        
        loginManager.logIn([ .publicProfile, .email ], viewController: view, completion: {
            loginResult in
            
            switch loginResult {
            case .failed(let error):
                failure(error as! String)
            case .cancelled:
                failure("Request cancelled")
            case .success(let accessToken):
                print("Login successful, token: \(accessToken)")
                completion(FBSDKAccessToken.current().tokenString)
            }
        })
    }
    
    private class func sendGoogleLoginRequest() {
        
    }
    
    private class func sendTwitterLoginRequest() {
        
    }
    
    static func sendRequest(_ endpoint: String, _ method: HTTPMethod, parameters: [String : NSDictionary]? = nil,
                            completion: @escaping (Data) -> (),
                            error: ((Void) -> ())? = nil,
                            failure: ((Void) -> ())? = nil) {
        
        let url = baseApiAddress + endpoint
        let headers = createHeaders()
        
        print(url)
        
        Alamofire.request(url, method: method, encoding: JSONEncoding.default, headers: headers).validate().responseJSON {
            (response) in
            
            switch response.result {
            case .success:
                print("Alamofire Response: \(response)")
                
                if let data = response.data {
                    completion(data)
                }
                else {
                    error?()
                }
                
            case .failure(let error):
                if let data = response.data {
                    let json = JSON(data: data)
                    print(json)
                }

                failure?()
            }
        }
    }
    
    private static func createHeaders() -> Dictionary<String, String> {
        let authHeader = "Bearer \(KeychainManager.loadToken()!)"
        return ["Authorization": authHeader]
    }
}
