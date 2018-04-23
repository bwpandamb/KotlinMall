package com.kotlin.user.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by mac on 2018/4/10.
 */
class UserServiceIml @Inject constructor(): UserService {

    @Inject
    lateinit var userRepository :UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

//        val userRepository = UserRepository()  //这里通过 dagger 引入了

//        return userRepository.register(mobile, pwd, verifyCode)   //这里通过编写一个BaseFunBoolean取代了所有这种类型的代码
//                .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
//                    override fun call(t: BaseResp<String>): Observable<Boolean> {
//                        if (t.status != 0) {
//                            return Observable.error(BaseException(t.status, t.message))
//                        }
//
//                        return Observable.just(true)
//                    }
//                })

        return userRepository.register(mobile, pwd, verifyCode)
                .convertBoolean()
    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return userRepository.login(mobile, pwd, pushId)
                .convert()
    }

}