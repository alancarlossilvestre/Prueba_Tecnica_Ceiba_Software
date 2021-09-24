package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.View.PostActivity
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.user_list_item.view.*



class UserAdapter(private val usuario: List<User>,)  :RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_list_item, parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usuario[position]
        holder.bindUser(user)
        holder.setOnClickListener()

    }


    override fun getItemCount(): Int = usuario.size

     class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

       val botonVP = itemView.findViewById<Button>(R.id.btn_view_post)
         var context =itemView.context
         fun bindUser(user: User){
             itemView.name.text = user.name
            itemView.phone.text = user.phone
            itemView.email.text = user.email

        }
         fun setOnClickListener() {
             botonVP.setOnClickListener(this)
         }

         override fun onClick(p0: View?) {
             val nameV  = itemView.findViewById<TextView>(R.id.name)
             val nameS:String  = nameV.text.toString()

             val phoneV  = itemView.findViewById<TextView>(R.id.phone)
             val phoneS:String  = phoneV.text.toString()

             val emailV  = itemView.findViewById<TextView>(R.id.email)
             val emailS:String  = emailV.text.toString()

             val intent = Intent(context, PostActivity::class.java)
             intent.putExtra("nombre", nameS)
             intent.putExtra("phone", phoneS)
             intent.putExtra("email", emailS)
             context.startActivity(intent)

         }

     }

    fun updateList(list: MutableList<User>){

        list.also {it }
        notifyDataSetChanged()

    }

}





