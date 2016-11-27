//
//  User.swift
//  GetQuizzd
//
//  Created by Krešimir Valjevac on 26/11/2016.
//  Copyright © 2016 Infinum Ltd. All rights reserved.
//

import Foundation
import Unbox
import ObjectMapper

class User : NSObject, Unboxable, Mappable {
    var avatar: String?
    var createdAt: String?
    var email: String?
    var id: Int?
    var nickname: String?
    var registered: Int?
    var updateAt: String?
    
    required init(unboxer: Unboxer) {
        avatar = unboxer.unbox(keyPath: "avatar")
        createdAt = unboxer.unbox(keyPath: "created_at")
        email = unboxer.unbox(keyPath: "email")
        id = unboxer.unbox(keyPath: "id")
        nickname = unboxer.unbox(keyPath: "nickname")
        registered = unboxer.unbox(keyPath: "registered")
        updateAt = unboxer.unbox(keyPath: "updated_at")
    }
    
    required init?(map: Map) {
        
    }

    func mapping(map: Map) {
        avatar <- map["avatar"]
        createdAt <- map["created_at"]
        email <- map["email"]
        id <- map["id"]
        nickname <- map["nickname"]
        registered <- map["registered"]
        updateAt <- map["updated_at"]
    }
    
    override init() {
        self.avatar = nil
        self.createdAt = nil
        self.email = nil
        self.id = nil
        self.nickname = nil
        self.registered = nil
        self.updateAt = nil
    }
}
