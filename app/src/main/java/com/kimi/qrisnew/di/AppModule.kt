package com.kimi.qrisnew.di

import android.content.Context
import androidx.room.Room
import com.kimi.qrisnew.dao.TransactionDatabaseDao
import com.kimi.qrisnew.database.TransactionDatabase
import com.kimi.qrisnew.network.BannerApi
import com.kimi.qrisnew.repository.BannerRepository
import com.kimi.qrisnew.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideBannerRepository(api: BannerApi) = BannerRepository(api)


    @Singleton
    @Provides
    fun provideBannerApi(): BannerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BannerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTransactionDao(transactionDatabase: TransactionDatabase): TransactionDatabaseDao =
        transactionDatabase.transactionDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): TransactionDatabase =
        Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            "transaction_tbl"
        )
            .fallbackToDestructiveMigration()
            .build()


}