package vn.com.tma.vo_ngoc_hanh.mychat.base.di

import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountDAO
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.AccountLocalDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.AccountRemoteDataSource
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.AccountRepository
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.data_source.IAccountDataSource

class Injection {
    companion object {
        fun injectAccountLocalDataSource(dao: AccountDAO) : IAccountDataSource{
            return AccountLocalDataSource.getInstance(dao)
        }

        fun injectAccountRemoteDataSource() : IAccountDataSource{
            return AccountRemoteDataSource.getInstance()
        }

        fun injectAccountRepository(dao: AccountDAO) : IAccountDataSource{
            val localDataSource = injectAccountLocalDataSource(dao)
            val remoteDataSource = injectAccountRemoteDataSource()

            return AccountRepository.getInstance(localDataSource, remoteDataSource)
        }
    }
}