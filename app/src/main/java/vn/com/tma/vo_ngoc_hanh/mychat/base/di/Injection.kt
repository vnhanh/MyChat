package vn.com.tma.vo_ngoc_hanh.mychat.base.di

import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.AccountDAO
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.AccountLocalDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.AccountRemoteDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.AccountRepository
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.source.IAccountDataSource

class Injection {
    companion object {
        fun injectAccountLocalDataSource(dao:AccountDAO) : IAccountDataSource{
            return AccountLocalDataSource.getInstance(dao)
        }

        fun injectAccountRemoteDataSource() : IAccountDataSource{
            return AccountRemoteDataSource.getInstance()
        }

        fun injectAccountRepository(dao:AccountDAO) : IAccountDataSource{
            val localDataSource = injectAccountLocalDataSource(dao)
            val remoteDataSource = injectAccountRemoteDataSource()

            return AccountRepository.getInstance(localDataSource, remoteDataSource)
        }
    }
}