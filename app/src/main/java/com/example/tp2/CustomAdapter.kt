import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2.Database
import com.example.tp2.ItemsViewModel
import com.example.tp2.LyricsActivity
import com.example.tp2.R


class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val BASE_URL: String = "https://api.lyrics.ovh/v1/"

    // Create views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        // Handle click on item
        view.setOnClickListener{
            val selectedItem = view.findViewById<TextView>(R.id.textView).text.toString()
            val data = selectedItem.split(" -- ")
            val monIntent = Intent(parent.context, LyricsActivity::class.java)

            val artist = data[0]
            val title = data[1]
            monIntent.putExtra("artist", artist)
            monIntent.putExtra("title", title)
            monIntent.putExtra("url_shared", "$BASE_URL$artist/$title")

            val datab = Database(parent.context)
            datab.updateDB(artist, title)
            parent.context.startActivity(monIntent)
        }

        return ViewHolder(view)
    }

    // Binds the items to the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.textView.text = ItemsViewModel.text

    }

    // Returns nb item to create
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

}
