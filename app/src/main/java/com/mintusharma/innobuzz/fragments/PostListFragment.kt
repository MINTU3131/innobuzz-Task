package com.mintusharma.innobuzz.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintusharma.innobuzz.OnClickListner
import com.mintusharma.innobuzz.R
import com.mintusharma.innobuzz.RetrofitClient
import com.mintusharma.innobuzz.adapters.PostsListAdapter
import com.mintusharma.innobuzz.databinding.FragmentPostListBinding
import com.mintusharma.innobuzz.db.PostsDatabase
import com.mintusharma.innobuzz.models.Post
import com.mintusharma.innobuzz.repositries.PostRepository
import com.mintusharma.innobuzz.viewmodel.MainViewModel
import com.mintusharma.innobuzz.viewmodel.MainViewModelFactory


class PostListFragment : Fragment() {
    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostListBinding.inflate(inflater, container, false)

        val db = PostsDatabase.getDatabase(requireActivity())
        val postRepository = PostRepository(RetrofitClient.apiServices,db,requireActivity())

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(postRepository)).get(MainViewModel::class.java)


        mainViewModel.posts.observe(requireActivity(), Observer {
            Log.d("MintuDatabase", it.toString())
            setUpRecyclerView(it);
//            Toast.makeText(context,it.size.toString(), Toast.LENGTH_LONG).show()
        })
        mainViewModel.fetchPosts()

        return binding.root
    }

    private fun setUpRecyclerView(list: List<Post>) {

        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.cartRec.layoutManager = layoutManager
        val adapter = PostsListAdapter(list as ArrayList<Post>,object : OnClickListner {
            override fun onTaskItemClick(post: Post) {
                val postDetail = PostDetailFragment.newInstance(post.title, post.body)
                val ft = requireActivity().supportFragmentManager.beginTransaction()
                ft.add(R.id.list_fragment_container, postDetail)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.addToBackStack(null)
                ft.commit()
            }

        })
        binding.cartRec.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}