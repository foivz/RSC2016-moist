//
//  File.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 26/11/2016.
//  Copyright © 2016 Infinum Ltd. All rights reserved.
//

import Foundation
import Unbox

class Response : Unboxable {
    let token: String
    let user: User
    
    required init(unboxer: Unboxer) throws {
        token = try unboxer.unbox(keyPath: "response.token")
        user = try unboxer.unbox(keyPath: "response.user")
    }
}
