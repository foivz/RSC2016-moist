//
//  Requesst.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 27/11/2016.
//  Copyright © 2016 Infinum Ltd. All rights reserved.
//

import Foundation
import ObjectMapper

class Request : Mappable {
    var user: User
    
    required init?(map: Map) {
        self.user = User()
    }
    
    func mapping(map: Map) {
        user <- map["user"]
    }
}
