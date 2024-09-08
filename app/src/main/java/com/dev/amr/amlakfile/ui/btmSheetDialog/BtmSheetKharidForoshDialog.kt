package com.dev.amr.amlakfile.ui.btmSheetDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.adapter.BtmSheetListAdapter
import com.dev.amr.amlakfile.databinding.BtmSheetDialogRowBinding
import com.dev.amr.amlakfile.utils.GetListItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BtmSheetKharidForoshDialog : BottomSheetDialogFragment(),View.OnClickListener{

    private lateinit var binding : BtmSheetDialogRowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BtmSheetDialogRowBinding.inflate(layoutInflater)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTitle.text = resources.getString(R.string.txt_type_user)
        binding.btnCancel.setOnClickListener(this)

        val getListItem = GetListItem()
        getListItem.getListItem()

        binding.recycler.adapter = BtmSheetListAdapter(getListItem)
        binding.recycler.layoutManager = GridLayoutManager(requireActivity(),1)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_cancel ->{
                dismiss()
            }
        }
    }
}