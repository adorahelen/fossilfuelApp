package dcu.fossilfuel.data.api

import dcu.fossilfuel.data.model.Guestbook
import dcu.fossilfuel.data.model.SignupDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("guestbook/get")
    fun getGuestbookEntries(): Call<List<Guestbook>>

    @POST("guestbook/post")
    fun addGuestbookEntry(@Body entry: Guestbook): Call<Guestbook>

    @POST("members/register")
    fun registerUser(@Body signupDto: SignupDto): Call<ResponseBody>
}