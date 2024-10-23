package com.dev.amr.amlakfile.ui.fragment.nav_main_fragment.btmsheet_items_add.register_buy_sell_fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.room.Room
import com.bumptech.glide.Glide
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.base.BaseFragment
import com.dev.amr.amlakfile.base.BaseLiveDialog
import com.dev.amr.amlakfile.data.db.DBRoom
import com.dev.amr.amlakfile.data.model.model.PersianInputFilter
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormOne
import com.dev.amr.amlakfile.databinding.ActivityTestMainBinding
import com.dev.amr.amlakfile.ui.btmSheetDialog.BtmSheetKharidForoshDialog
import com.dev.amr.amlakfile.utils.NumberTextWatcher
import com.github.yamin8000.ppn.PersianDigits
import java.util.regex.Pattern

class RegisterBuyAndSellFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: ActivityTestMainBinding
    private lateinit var countGetList: Number
    private var cameraRequest: Int = 0
    private lateinit var cameraIntent: Intent
    private lateinit var db: DBRoom
    private lateinit var animation: Animation


    private lateinit var userRegistering: String
    private lateinit var DateRegistering: String
    private lateinit var TimeRegistering: String
    private lateinit var ownerName: String
    private lateinit var ownerFamily: String
    private lateinit var ownerMobilePhone: String
    private lateinit var addressFile: String
    private lateinit var metrazhMoraba: String
    private lateinit var sureVame: String
    private lateinit var priceMelk: String
    private lateinit var description: String

    private lateinit var zAnim: Animation
    val pattern = Pattern.compile("[a-zA-Z]+$")

    private lateinit var user: String
    private lateinit var ownerPhone: String
    private lateinit var cabinets: String
    private lateinit var counterAllTabaghat: String
    private lateinit var counterAllVahedha: String
    private lateinit var counterVahedhaDarTabaghe: String
    private lateinit var jahatSakhteman: String
    private lateinit var jahatVahed: String
    private lateinit var jensKaf: String
    private lateinit var garmayesh: String
    private lateinit var sarmayesh: String
    private lateinit var typeUser: String
    private lateinit var typeSanad: String
    private lateinit var location: String
    private lateinit var ageBana: String
    private lateinit var tabaghe: String
    private lateinit var cunterOtagh: String
    private lateinit var vaziyatMelk: String
    private lateinit var wC: String
    private lateinit var nemaSakhteman: String

    private lateinit var checkAsansor: String
    private lateinit var checkMelkBazsaziShode: String
    private lateinit var checkAnbari: String
    private lateinit var checkParking: String
    private lateinit var checkEstakhr: String
    private lateinit var checkIphonTasviri: String
    private lateinit var checkBalkon: String
    private lateinit var checkSona: String
    private lateinit var checkGazRomizi: String
    private lateinit var checkHod: String
    private lateinit var checkKomodDivari: String
    private lateinit var checkDarbZedSerghat: String
    private lateinit var checkHayat: String
    private lateinit var checkJakozi: String
    private lateinit var checkLabi: String
    private lateinit var checkSalonVarzeshi: String
    private lateinit var checkSalonEjtemaat: String
    private lateinit var checkAntenMarkazi: String
    private lateinit var checkJarobarghiMarkazi: String
    private var checkSelectAll: String = ""

    private var photo1: String = ""
    private var photo2: String = ""
    private var photo3: String = ""
    private var photo4: String = ""
    private var photo5: String = ""
    private var photo6: String = ""

    private var checkForms: Int = 1

    private var btmSheetKharidForosh: BtmSheetKharidForoshDialog = BtmSheetKharidForoshDialog()

    private var counter: Int = 1
    private var counterStepsOne: Int = 1
    private var counterStepsTwo: Int = 1
    private lateinit var newMonth: String
    private lateinit var newDay: String
    private var month = 0
    private var day = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ActivityTestMainBinding.inflate(layoutInflater)
        zAnim = AnimationUtils.loadAnimation(requireActivity(), R.anim.zoom);
//        onClickViews()
//
        db = Room.databaseBuilder(requireActivity(), DBRoom::class.java, "amlak_db")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

        getCurrentDate(binding.layFormOne.edtDate, binding.layFormOne.edtTime)

        binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
        binding.layFormOne.edtUserRegistering.requestFocus()
        binding.toolbar.layLogo.visibility = View.GONE
        binding.toolbar.layBtnBack.visibility = View.VISIBLE
        textWatchers()
        binding.rBFormOne.setOnClickListener(this)
        binding.rBFormTow.setOnClickListener(this)
        binding.btnNext123.setOnClickListener(this)
        binding.btnPrevious.setOnClickListener(this)
        binding.layFormFive.radioBtn1.setOnClickListener(this)
        binding.layFormFive.radioBtn2.setOnClickListener(this)

        BaseLiveDialog.liveDataEmptyItems.observe(requireActivity()) {
            itemsEmptyForms()
        }

        BaseLiveDialog.liveDataBackToHomePage.observe(requireActivity()) {
            if (it != null) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_registerBuyAndSellFragment_to_mainFragment)
            }
        }

        return binding.root
    }


//    private fun onClickViews() {
//        binding.toolbar.txtTitle.text = getString(R.string.txt_register_file_kharid_forosh)
//        binding.toolbar.btnMenu.visibility = View.GONE
//        binding.btnCancel.setOnClickListener(this)
//        binding.laySave.setOnClickListener(this)
//        binding.layFormTow.edtTypeUser.setOnClickListener(this)
//        binding.layFormTow.edtTypeSanad.setOnClickListener(this)
//        binding.layFormTow.edtAgeBana.setOnClickListener(this)
//        binding.layFormTow.edtTabaghe.setOnClickListener(this)
//        binding.layFormTow.edtCunterOtagh.setOnClickListener(this)
//        binding.layFormTow.edtVaziyadMelk.setOnClickListener(this)
//        binding.layFormTow.edtWc.setOnClickListener(this)
//        binding.layFormTow.edtNemaSakhteman.setOnClickListener(this)
//        binding.layFormTow.edtCabinets.setOnClickListener(this)
//        binding.layFormTow.edtJahatSakhteman.setOnClickListener(this)
//        binding.layFormTow.edtJahatVahed.setOnClickListener(this)
//        binding.layFormTow.edtJensKaf.setOnClickListener(this)
//        binding.layFormTow.edtGarmayesh.setOnClickListener(this)
//        binding.layFormTow.edtSarmayesh.setOnClickListener(this)
//        binding.layFormTow.layImg1.setOnClickListener(this)
//        binding.layFormTow.img1.setOnClickListener(this)
//        binding.layFormTow.txtImg1.setOnClickListener(this)
//        binding.layFormTow.layImg2.setOnClickListener(this)
//        binding.layFormTow.img2.setOnClickListener(this)
//        binding.layFormTow.txtImg2.setOnClickListener(this)
//        binding.layFormTow.layImg3.setOnClickListener(this)
//        binding.layFormTow.img3.setOnClickListener(this)
//        binding.layFormTow.txtImg3.setOnClickListener(this)
//        binding.layFormTow.layImg4.setOnClickListener(this)
//        binding.layFormTow.img4.setOnClickListener(this)
//        binding.layFormTow.txtImg4.setOnClickListener(this)
//        binding.layFormTow.layImg5.setOnClickListener(this)
//        binding.layFormTow.img5.setOnClickListener(this)
//        binding.layFormTow.txtImg5.setOnClickListener(this)
//        binding.layFormTow.layImg6.setOnClickListener(this)
//        binding.layFormTow.img6.setOnClickListener(this)
//        binding.layFormTow.txtImg6.setOnClickListener(this)
//        binding.layFormTow.btnImgDelete1.setOnClickListener(this)
//        binding.layFormTow.btnImgDelete2.setOnClickListener(this)
//        binding.layFormTow.btnImgDelete3.setOnClickListener(this)
//        binding.layFormTow.btnImgDelete4.setOnClickListener(this)
//        binding.layFormTow.btnImgDelete5.setOnClickListener(this)
//        binding.layFormTow.btnImgDelete6.setOnClickListener(this)
//        binding.layFormTow.checkSelectAll.setOnClickListener(this)
//        binding.layFormTow.btnShowItems.setOnClickListener(this)
//        binding.layFormTow.imgShowItems.setOnClickListener(this)
//        binding.rBFormOne.setOnClickListener(this)
//        binding.rBFormTow.setOnClickListener(this)
//    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        BaseLiveDialog.liveDataTypeUser.observe(requireActivity()) {
//            try {
//                when (Hawks.getInt("countGetList")) {
//                    1 -> {
//                        binding.layFormTow.edtTypeUser.error = null
//                        binding.layFormTow.edtTypeUser.setText(Hawks.getString("item"))
//                    }
//
//                    2 -> {
//                        binding.layFormTow.edtTypeSanad.error = null
//                        binding.layFormTow.edtTypeSanad.setText(Hawks.getString("item"))
//                    }
//
//                    3 -> {
//                        binding.layFormTow.edtAgeBana.error = null
//                        binding.layFormTow.edtAgeBana.setText(Hawks.getString("item"))
//                    }
//
//                    4 -> {
//                        binding.layFormTow.edtTabaghe.error = null
//                        binding.layFormTow.edtTabaghe.setText(Hawks.getString("item"))
//                    }
//
//                    5 -> {
//                        binding.layFormTow.edtCunterOtagh.error = null
//                        binding.layFormTow.edtCunterOtagh.setText(Hawks.getString("item"))
//                    }
//
//                    6 -> {
//                        binding.layFormTow.edtVaziyadMelk.error = null
//                        binding.layFormTow.edtVaziyadMelk.setText(Hawks.getString("item"))
//                    }
//
//                    7 -> {
//                        binding.layFormTow.edtWc.error = null
//                        binding.layFormTow.edtWc.setText(Hawks.getString("item"))
//                    }
//
//                    8 -> {
//                        binding.layFormTow.edtNemaSakhteman.error = null
//                        binding.layFormTow.edtNemaSakhteman.setText(Hawks.getString("item"))
//                    }
//
//                    9 -> {
//                        binding.layFormTow.edtCabinets.error = null
//                        binding.layFormTow.edtCabinets.setText(Hawks.getString("item"))
//                    }
//
//                    10 -> {
//                        binding.layFormTow.edtJahatSakhteman.error = null
//                        binding.layFormTow.edtJahatSakhteman.setText(Hawks.getString("item"))
//                    }
//
//                    11 -> {
//                        binding.layFormTow.edtJahatVahed.error = null
//                        binding.layFormTow.edtJahatVahed.setText(Hawks.getString("item"))
//                    }
//
//                    12 -> {
//                        binding.layFormTow.edtJensKaf.error = null
//                        binding.layFormTow.edtJensKaf.setText(Hawks.getString("item"))
//                    }
//
//                    13 -> {
//                        binding.layFormTow.edtGarmayesh.error = null
//                        binding.layFormTow.edtGarmayesh.setText(Hawks.getString("item"))
//                    }
//
//                    14 -> {
//                        binding.layFormTow.edtSarmayesh.error = null
//                        binding.layFormTow.edtSarmayesh.setText(Hawks.getString("item"))
//                    }
//                }
//                btmSheetKharidForosh.dismiss()
//            } catch (e: Exception) {
//                e.message
//            }
//        }
//
//        BaseLiveDialog.liveDataBackToHomePage.observe(requireActivity()) {
//            if (it != null) {
//                Navigation.findNavController(binding.root)
//                    .navigate(R.id.action_registerBuyAndSellFragment_to_mainFragment)
//            }
//        }
//
//        BaseLiveDialog.liveDataEmptyItems.observe(requireActivity()) {
//            itemsEmptyForms()
//        }
//
//        binding.layFormOne.edtPriceMelk.doAfterTextChanged {
//            if (binding.layFormOne.edtPriceMelk.length() == 0) {
//                binding.layFormOne.txtPriceMelk.visibility = View.GONE
//            } else if (binding.layFormOne.edtPriceMelk.length() != 0) {
//                binding.layFormOne.txtPriceMelk.visibility = View.VISIBLE
//                val number = it.toString()
//                binding.layFormOne.txtPriceMelk.text =
//                    PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
//            }
//        }
//
//        binding.layFormOne.edtPriceMelk.addTextChangedListener(
//            NumberTextWatcher(binding.layFormOne.edtPriceMelk)
//        )
//        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) ==
//            PackageManager.PERMISSION_DENIED
//        )
//
//
//            binding.layFormTow.edtPriceMelk.doAfterTextChanged {
//                if (binding.layFormTow.edtPriceMelk.length() == 0) {
//                    binding.layFormTow.txtPriceMelk.visibility = View.GONE
//                } else if (binding.layFormTow.edtPriceMelk.length() != 0) {
//                    binding.layFormTow.txtPriceMelk.visibility = View.VISIBLE
//                    val number = it.toString()
//                    binding.layFormTow.txtPriceMelk.text =
//                        PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
//                }
//            }
//
//        binding.layFormTow.edtPriceMelk.addTextChangedListener(
//            NumberTextWatcher(binding.layFormTow.edtPriceMelk)
//        )
//        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) ==
//            PackageManager.PERMISSION_DENIED
//        )
//
//            ActivityCompat.requestPermissions(
//                requireActivity(), arrayOf(Manifest.permission.CAMERA), cameraRequest
//            )

    }

//    private fun validationDisabled() {
//        when (checkForms) {
//            1 -> {
//                binding.layFormTow.edtUserRegistering.error = null
//                binding.layFormTow.edtOwnerName.error = null
//                binding.layFormTow.edtOwnerPhone.error = null
//                binding.layFormTow.edtCabinets.error = null
//                binding.layFormTow.edtJahatSakhteman.error = null
//                binding.layFormTow.edtJahatVahed.error = null
//                binding.layFormTow.edtJensKaf.error = null
//                binding.layFormTow.edtGarmayesh.error = null
//                binding.layFormTow.edtSarmayesh.error = null
//                binding.layFormTow.edtAddressFile.error = null
//                binding.layFormTow.edtCounterAllTabaghat.error = null
//                binding.layFormTow.edtCounterAllVahedha.error = null
//                binding.layFormTow.edtCounterVahedhaDarTabaghe.error = null
//                binding.layFormTow.edtTypeUser.error = null
//                binding.layFormTow.edtTypeSanad.error = null
//                binding.layFormTow.edtMetrazhMoraba.error = null
//                binding.layFormTow.edtLocation.error = null
//                binding.layFormTow.edtAgeBana.error = null
//                binding.layFormTow.edtTabaghe.error = null
//                binding.layFormTow.edtCunterOtagh.error = null
//                binding.layFormTow.edtVaziyadMelk.error = null
//                binding.layFormTow.edtPriceMelk.error = null
//                binding.layFormTow.edtSureVame.error = null
//                binding.layFormTow.edtWc.error = null
//                binding.layFormTow.edtNemaSakhteman.error = null
//            }
//
//            2 -> {
//                binding.layFormOne.edtUserRegistering.error = null
//                binding.layFormOne.edtOwnerName.error = null
//                binding.layFormOne.edtOwnerPhone.error = null
//                binding.layFormOne.edtAddressFile.error = null
//                binding.layFormOne.edtMetrazhMoraba.error = null
//                binding.layFormOne.edtSureVame.error = null
//                binding.layFormOne.edtPriceMelk.error = null
//            }
//        }
//    }
//

//
//    override fun onClick(v: View?) {
//        cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//        when (v?.id) {
//            R.id.btn_cancel -> {
//                Navigation.findNavController(binding.root)
//                    .navigate(R.id.action_registerBuyMortgageAndRentFragment_to_mainFragment)
//            }
//
//            R.id.edt_type_user -> {
//                countGetList = 1
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//            }
//
//            R.id.edt_type_sanad -> {
//                countGetList = 2
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_age_bana -> {
//                countGetList = 3
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_tabaghe -> {
//                countGetList = 4
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_cunter_otagh -> {
//                countGetList = 5
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_vaziyad_melk -> {
//                countGetList = 6
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_wc -> {
//                countGetList = 7
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_nema_sakhteman -> {
//                countGetList = 8
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_cabinets -> {
//                countGetList = 9
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_jahat_sakhteman -> {
//                countGetList = 10
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_jahat_vahed -> {
//                countGetList = 11
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_jens_kaf -> {
//                countGetList = 12
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_garmayesh -> {
//                countGetList = 13
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.edt_sarmayesh -> {
//                countGetList = 14
//                Hawks.save("countGetList", countGetList as Int)
//
//                btmSheetKharidForosh.show(childFragmentManager, "")
//                btmSheetKharidForosh.isCancelable = false
//
//            }
//
//            R.id.img_1,
//            R.id.txt_img_1 -> {
////                cameraRequest = 1
////                startActivityForResult(cameraIntent, cameraRequest)
//                showDialogInactive(requireActivity())
//            }
//
//            R.id.img_2,
//            R.id.txt_img_2 -> {
////                cameraRequest = 2
////                startActivityForResult(cameraIntent, cameraRequest)
//                showDialogInactive(requireActivity())
//            }
//
//            R.id.img_3,
//            R.id.txt_img_3 -> {
////                cameraRequest = 3
////                startActivityForResult(cameraIntent, cameraRequest)
//                showDialogInactive(requireActivity())
//            }
//
//            R.id.img_4,
//            R.id.txt_img_4 -> {
////                cameraRequest = 4
////                startActivityForResult(cameraIntent, cameraRequest)
//                showDialogInactive(requireActivity())
//            }
//
//            R.id.img_5,
//            R.id.txt_img_5 -> {
////                cameraRequest = 5
////                startActivityForResult(cameraIntent, cameraRequest)
//                showDialogInactive(requireActivity())
//            }
//
//            R.id.img_6,
//            R.id.txt_img_6 -> {
////                cameraRequest = 6
////                startActivityForResult(cameraIntent, cameraRequest)
//                showDialogInactive(requireActivity())
//            }
//
//            R.id.btn_img_delete1 -> {
//                binding.layFormTow.img1.visibility = View.VISIBLE
//                binding.layFormTow.txtImg1.visibility = View.VISIBLE
//                binding.layFormTow.image1.visibility = View.GONE
//                binding.layFormTow.btnImgDelete1.visibility = View.GONE
//                binding.layFormTow.image1.setImageBitmap(null)
//            }
//
//            R.id.btn_img_delete2 -> {
//                binding.layFormTow.img2.visibility = View.VISIBLE
//                binding.layFormTow.txtImg2.visibility = View.VISIBLE
//                binding.layFormTow.image2.visibility = View.GONE
//                binding.layFormTow.btnImgDelete2.visibility = View.GONE
//                binding.layFormTow.image2.setImageBitmap(null)
//            }
//
//            R.id.btn_img_delete3 -> {
//                binding.layFormTow.img3.visibility = View.VISIBLE
//                binding.layFormTow.txtImg3.visibility = View.VISIBLE
//                binding.layFormTow.image3.visibility = View.GONE
//                binding.layFormTow.btnImgDelete3.visibility = View.GONE
//                binding.layFormTow.image3.setImageBitmap(null)
//            }
//
//            R.id.btn_img_delete4 -> {
//                binding.layFormTow.img4.visibility = View.VISIBLE
//                binding.layFormTow.txtImg4.visibility = View.VISIBLE
//                binding.layFormTow.image4.visibility = View.GONE
//                binding.layFormTow.btnImgDelete4.visibility = View.GONE
//                binding.layFormTow.image4.setImageBitmap(null)
//            }
//
//            R.id.btn_img_delete5 -> {
//                binding.layFormTow.img5.visibility = View.VISIBLE
//                binding.layFormTow.txtImg5.visibility = View.VISIBLE
//                binding.layFormTow.image5.visibility = View.GONE
//                binding.layFormTow.btnImgDelete5.visibility = View.GONE
//                binding.layFormTow.image5.setImageBitmap(null)
//            }
//
//            R.id.btn_img_delete6 -> {
//                binding.layFormTow.img6.visibility = View.VISIBLE
//                binding.layFormTow.txtImg6.visibility = View.VISIBLE
//                binding.layFormTow.image6.visibility = View.GONE
//                binding.layFormTow.btnImgDelete6.visibility = View.GONE
//                binding.layFormTow.image6.setImageBitmap(null)
//            }
//
//            R.id.check_select_all -> {
//                if (binding.layFormTow.checkSelectAll.isChecked) {
//                    binding.layFormTow.checkAnbari.isChecked = true
//                    binding.layFormTow.checkAsansor.isChecked = true
//                    binding.layFormTow.checkBalkon.isChecked = true
//                    binding.layFormTow.checkParking.isChecked = true
//                    binding.layFormTow.checkIphonTasviri.isChecked = true
//                    binding.layFormTow.checkEstakhr.isChecked = true
//                    binding.layFormTow.checkSona.isChecked = true
//                    binding.layFormTow.checkGazRomizi.isChecked = true
//                    binding.layFormTow.checkHod.isChecked = true
//                    binding.layFormTow.checkDarbZedSerghat.isChecked = true
//                    binding.layFormTow.checkHayat.isChecked = true
//                    binding.layFormTow.checkJakozi.isChecked = true
//                    binding.layFormTow.checkLabi.isChecked = true
//                    binding.layFormTow.checkSalonVarzeshi.isChecked = true
//                    binding.layFormTow.checkSalonEjtemaat.isChecked = true
//                    binding.layFormTow.checkAntenMarkazi.isChecked = true
//                    binding.layFormTow.checkJarobarghiMarkazi.isChecked = true
//                    binding.layFormTow.checkKomodDivari.isChecked = true
//                    binding.layFormTow.checkMelkBazsaziShode.isChecked = true
//                } else {
//                    binding.layFormTow.checkAnbari.isChecked = false
//                    binding.layFormTow.checkAsansor.isChecked = false
//                    binding.layFormTow.checkBalkon.isChecked = false
//                    binding.layFormTow.checkParking.isChecked = false
//                    binding.layFormTow.checkIphonTasviri.isChecked = false
//                    binding.layFormTow.checkEstakhr.isChecked = false
//                    binding.layFormTow.checkSona.isChecked = false
//                    binding.layFormTow.checkGazRomizi.isChecked = false
//                    binding.layFormTow.checkHod.isChecked = false
//                    binding.layFormTow.checkDarbZedSerghat.isChecked = false
//                    binding.layFormTow.checkHayat.isChecked = false
//                    binding.layFormTow.checkJakozi.isChecked = false
//                    binding.layFormTow.checkLabi.isChecked = false
//                    binding.layFormTow.checkSalonVarzeshi.isChecked = false
//                    binding.layFormTow.checkSalonEjtemaat.isChecked = false
//                    binding.layFormTow.checkAntenMarkazi.isChecked = false
//                    binding.layFormTow.checkJarobarghiMarkazi.isChecked = false
//                    binding.layFormTow.checkKomodDivari.isChecked = false
//                    binding.layFormTow.checkMelkBazsaziShode.isChecked = false
//                }
//            }
//
//            R.id.img_show_items,
//            R.id.btn_show_items -> {
//                if (binding.layFormTow.layAnbari.visibility == View.GONE) {
//                    animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
//                    binding.layFormTow.layItemsRadio1.animation = animation
//                    binding.layFormTow.layItemsRadio2.animation = animation
//                    binding.layFormTow.btnShowItems.setText(R.string.txt_show_items_fewer)
//                    binding.layFormTow.imgShowItems.rotation = 90F
//                    binding.layFormTow.layAnbari.visibility = View.VISIBLE
//                    binding.layFormTow.layBalkon.visibility = View.VISIBLE
//                    binding.layFormTow.laySona.visibility = View.VISIBLE
//                    binding.layFormTow.layGazRomizi.visibility = View.VISIBLE
//                    binding.layFormTow.layHod.visibility = View.VISIBLE
//                    binding.layFormTow.layDarbZedSerghat.visibility = View.VISIBLE
//                    binding.layFormTow.layKomodDivari.visibility = View.VISIBLE
//                    binding.layFormTow.layHayat.visibility = View.VISIBLE
//                    binding.layFormTow.layEstakhr.visibility = View.VISIBLE
//                    binding.layFormTow.layIphonTasviri.visibility = View.VISIBLE
//                    binding.layFormTow.layLabi.visibility = View.VISIBLE
//                    binding.layFormTow.laySalonVarzeshi.visibility = View.VISIBLE
//                    binding.layFormTow.laySalonEjtemaat.visibility = View.VISIBLE
//                    binding.layFormTow.layAntenMarkazi.visibility = View.VISIBLE
//                    binding.layFormTow.layJarobarghiMarkazi.visibility = View.VISIBLE
//                    binding.layFormTow.laySelectAll.visibility = View.VISIBLE
//                } else if (binding.layFormTow.layAnbari.visibility == View.VISIBLE) {
//                    animation = AnimationUtils.loadAnimation(
//                        requireContext(), R.anim.slide_up
//
//                    )
//                    binding.layFormTow.layItemsRadio1.animation = animation
//                    binding.layFormTow.layItemsRadio2.animation = animation
//                    binding.layFormTow.btnShowItems.setText(R.string.txt_show_items_More)
//                    binding.layFormTow.imgShowItems.rotation = 270F
//                    binding.layFormTow.layAnbari.visibility = View.GONE
//                    binding.layFormTow.layBalkon.visibility = View.GONE
//                    binding.layFormTow.laySona.visibility = View.GONE
//                    binding.layFormTow.layGazRomizi.visibility = View.GONE
//                    binding.layFormTow.layHod.visibility = View.GONE
//                    binding.layFormTow.layDarbZedSerghat.visibility = View.GONE
//                    binding.layFormTow.layKomodDivari.visibility = View.GONE
//                    binding.layFormTow.layHayat.visibility = View.GONE
//                    binding.layFormTow.layEstakhr.visibility = View.GONE
//                    binding.layFormTow.layIphonTasviri.visibility = View.GONE
//                    binding.layFormTow.layLabi.visibility = View.GONE
//                    binding.layFormTow.laySalonVarzeshi.visibility = View.GONE
//                    binding.layFormTow.laySalonEjtemaat.visibility = View.GONE
//                    binding.layFormTow.layAntenMarkazi.visibility = View.GONE
//                    binding.layFormTow.layJarobarghiMarkazi.visibility = View.GONE
//                    binding.layFormTow.laySelectAll.visibility = View.GONE
//                }
//            }
//
//            R.id.lay_save -> {
//
//                when (checkForms) {
//                    1 -> {
//                        if (binding.layFormOne.edtUserRegistering.text.toString() == "" ||
//                            binding.layFormOne.edtOwnerName.text.toString() == "" ||
//                            binding.layFormOne.edtOwnerPhone.text.toString() == "" ||
//                            binding.layFormOne.edtAddressFile.text.toString() == "" ||
//                            binding.layFormOne.edtSureVame.text.toString() == "" ||
//                            binding.layFormOne.edtMetrazhMoraba.text.toString() == "" ||
//                            binding.layFormOne.edtPriceMelk.text.toString() == ""
//                        ) {
//
////                    showDialogEmpty(requireActivity())
//
//                            if (binding.layFormOne.edtUserRegistering.text.toString() == "") {
//                                binding.layFormOne.edtUserRegistering.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormOne.edtOwnerName.text.toString() == "") {
//                                binding.layFormOne.edtOwnerName.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormOne.edtOwnerPhone.text.toString() == "") {
//                                binding.layFormOne.edtOwnerPhone.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//
//                            if (binding.layFormOne.edtAddressFile.text.toString() == "") {
//                                binding.layFormOne.edtAddressFile.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//
//                            if (binding.layFormOne.edtSureVame.text.toString() == "") {
//                                binding.layFormOne.edtSureVame.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//
//                            if (binding.layFormOne.edtMetrazhMoraba.text.toString() == "") {
//                                binding.layFormOne.edtMetrazhMoraba.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//
//                            if (binding.layFormOne.edtPriceMelk.text.toString() == "") {
//                                binding.layFormOne.edtPriceMelk.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//
//                        } else {
//                            user = binding.layFormOne.edtUserRegistering.text.toString()
//                            ownerName = binding.layFormOne.edtOwnerName.text.toString()
//                            ownerPhone = binding.layFormOne.edtOwnerPhone.text.toString()
//                            addressFile = binding.layFormOne.edtAddressFile.text.toString()
//                            sureVame = binding.layFormOne.edtSureVame.text.toString()
//                            metrazhMoraba =
//                                binding.layFormOne.edtMetrazhMoraba.text.toString() + "متر"
//                            priceMelk = binding.layFormOne.edtPriceMelk.text.toString() + " , " +
//                                    binding.layFormOne.txtPriceMelk.text.toString()
//
//                            val registerBuyAndSellFormOne = RegisterBuyAndSellModelFormOne(
//                                0,
//                                user,
//                                ownerName,
//                                ownerPhone,
//                                addressFile,
//                                description,
//                                sureVame,
//                                metrazhMoraba,
//                                priceMelk,
//                            )
//
//                            val result = db.dBDao()
//                                .upsertRegisterBuyAndSellFormOne(registerBuyAndSellFormOne)
//                            if (result > 0) {
//                                showDialogDoYouContinue(requireActivity())
////                        agreeDialog()
//                            } else {
//                                Toast.makeText(
//                                    requireActivity(),
//                                    getString(R.string.txt_save_infonmation_failure),
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//                    }
//
//                    2 -> {
//                        if (binding.layFormTow.edtUserRegistering.text.toString() == "" ||
//                            binding.layFormTow.edtOwnerName.text.toString() == "" ||
//                            binding.layFormTow.edtOwnerPhone.text.toString() == "" ||
//                            binding.layFormTow.edtCabinets.text.toString() == "" ||
//                            binding.layFormTow.edtJahatSakhteman.text.toString() == "" ||
//                            binding.layFormTow.edtJahatVahed.text.toString() == "" ||
//                            binding.layFormTow.edtJensKaf.text.toString() == "" ||
//                            binding.layFormTow.edtGarmayesh.text.toString() == "" ||
//                            binding.layFormTow.edtSarmayesh.text.toString() == "" ||
//                            binding.layFormTow.edtAddressFile.text.toString() == "" ||
//                            binding.layFormTow.edtCounterAllTabaghat.text.toString() == "" ||
//                            binding.layFormTow.edtCounterAllVahedha.text.toString() == "" ||
//                            binding.layFormTow.edtCounterVahedhaDarTabaghe.text.toString() == "" ||
//                            binding.layFormTow.edtTypeUser.text.toString() == "" ||
//                            binding.layFormTow.edtTypeSanad.text.toString() == "" ||
//                            binding.layFormTow.edtMetrazhMoraba.text.toString() == "" ||
//                            binding.layFormTow.edtLocation.text.toString() == "" ||
//                            binding.layFormTow.edtAgeBana.text.toString() == "" ||
//                            binding.layFormTow.edtTabaghe.text.toString() == "" ||
//                            binding.layFormTow.edtCunterOtagh.text.toString() == "" ||
//                            binding.layFormTow.edtVaziyadMelk.text.toString() == "" ||
//                            binding.layFormTow.edtSureVame.text.toString() == "" ||
//                            binding.layFormTow.edtPriceMelk.text.toString() == "" ||
//                            binding.layFormTow.edtWc.text.toString() == "" ||
//                            binding.layFormTow.edtNemaSakhteman.text.toString() == ""
//                        ) {
//
////                    showDialogEmpty(requireActivity())
//
//                            if (binding.layFormTow.edtUserRegistering.text.toString() == "") {
//                                binding.layFormTow.edtUserRegistering.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtOwnerName.text.toString() == "") {
//                                binding.layFormTow.edtOwnerName.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtOwnerPhone.text.toString() == "") {
//                                binding.layFormTow.edtOwnerPhone.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtCabinets.text.toString() == "") {
//                                binding.layFormTow.edtCabinets.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtJahatSakhteman.text.toString() == "") {
//                                binding.layFormTow.edtJahatSakhteman.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtJahatVahed.text.toString() == "") {
//                                binding.layFormTow.edtJahatVahed.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtJensKaf.text.toString() == "") {
//                                binding.layFormTow.edtJensKaf.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtGarmayesh.text.toString() == "") {
//                                binding.layFormTow.edtGarmayesh.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtSarmayesh.text.toString() == "") {
//                                binding.layFormTow.edtSarmayesh.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtAddressFile.text.toString() == "") {
//                                binding.layFormTow.edtAddressFile.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//
//                            if (binding.layFormTow.edtCounterAllTabaghat.text.toString() == "") {
//                                binding.layFormTow.edtCounterAllTabaghat.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtCounterAllVahedha.text.toString() == "") {
//                                binding.layFormTow.edtCounterAllVahedha.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtCounterVahedhaDarTabaghe.text.toString() == "") {
//                                binding.layFormTow.edtCounterVahedhaDarTabaghe.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtTypeUser.text.toString() == "") {
//                                binding.layFormTow.edtTypeUser.error = " "
//                            }
//                            if (binding.layFormTow.edtTypeSanad.text.toString() == "") {
//                                binding.layFormTow.edtTypeSanad.error = " "
//                            }
//                            if (binding.layFormTow.edtMetrazhMoraba.text.toString() == "") {
//                                binding.layFormTow.edtMetrazhMoraba.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtLocation.text.toString() == "") {
//                                binding.layFormTow.edtLocation.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtAgeBana.text.toString() == "") {
//                                binding.layFormTow.edtAgeBana.error = " "
//                            }
//                            if (binding.layFormTow.edtTabaghe.text.toString() == "") {
//                                binding.layFormTow.edtTabaghe.error = " "
//                            }
//                            if (binding.layFormTow.edtCunterOtagh.text.toString() == "") {
//                                binding.layFormTow.edtCunterOtagh.error = " "
//                            }
//                            if (binding.layFormTow.edtVaziyadMelk.text.toString() == "") {
//                                binding.layFormTow.edtVaziyadMelk.error = " "
//                            }
//                            if (binding.layFormTow.edtSureVame.text.toString() == "") {
//                                binding.layFormTow.edtSureVame.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtPriceMelk.text.toString() == "") {
//                                binding.layFormTow.edtPriceMelk.error =
//                                    getString(R.string.txt_ejbari_mibashad)
//                            }
//                            if (binding.layFormTow.edtWc.text.toString() == "") {
//                                binding.layFormTow.edtWc.error = " "
//                            }
//                            if (binding.layFormTow.edtNemaSakhteman.text.toString() == "") {
//                                binding.layFormTow.edtNemaSakhteman.error = " "
//                            }
//
//                        } else {
//                            user = binding.layFormTow.edtUserRegistering.text.toString()
//                            ownerName = binding.layFormTow.edtOwnerName.text.toString()
//                            ownerPhone = binding.layFormTow.edtOwnerPhone.text.toString()
//                            cabinets = binding.layFormTow.edtCabinets.text.toString()
//                            jahatSakhteman = binding.layFormTow.edtJahatSakhteman.text.toString()
//                            jahatVahed = binding.layFormTow.edtJahatVahed.text.toString()
//                            jensKaf = binding.layFormTow.edtJensKaf.text.toString()
//                            garmayesh = binding.layFormTow.edtGarmayesh.text.toString()
//                            sarmayesh = binding.layFormTow.edtSarmayesh.text.toString()
//                            addressFile = binding.layFormTow.edtAddressFile.text.toString()
//                            counterAllTabaghat =
//                                binding.layFormTow.edtCounterAllTabaghat.text.toString()
//                            counterAllVahedha =
//                                binding.layFormTow.edtCounterAllVahedha.text.toString()
//                            counterVahedhaDarTabaghe =
//                                binding.layFormTow.edtCounterVahedhaDarTabaghe.text.toString()
//                            typeUser = binding.layFormTow.edtTypeUser.text.toString()
//                            typeSanad = binding.layFormTow.edtTypeSanad.text.toString()
//                            metrazhMoraba =
//                                binding.layFormTow.edtMetrazhMoraba.text.toString() + "متر"
//                            location = binding.layFormTow.edtLocation.text.toString()
//                            ageBana = binding.layFormTow.edtAgeBana.text.toString()
//                            tabaghe = binding.layFormTow.edtTabaghe.text.toString()
//                            cunterOtagh = binding.layFormTow.edtCunterOtagh.text.toString()
//                            vaziyatMelk = binding.layFormTow.edtVaziyadMelk.text.toString()
//                            sureVame = binding.layFormTow.edtSureVame.text.toString()
//                            priceMelk = binding.layFormTow.edtPriceMelk.text.toString() + " , " +
//                                    binding.layFormTow.txtPriceMelk.text.toString()
//                            wC = binding.layFormTow.edtWc.text.toString()
//                            nemaSakhteman = binding.layFormTow.edtNemaSakhteman.text.toString()
//
//                            checkAsansor = if (binding.layFormTow.checkAsansor.isChecked) {
//                                binding.layFormTow.checkAsansor.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkMelkBazsaziShode =
//                                if (binding.layFormTow.checkMelkBazsaziShode.isChecked) {
//                                    binding.layFormTow.checkMelkBazsaziShode.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            checkAnbari = if (binding.layFormTow.checkAnbari.isChecked) {
//                                binding.layFormTow.checkAnbari.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkBalkon = if (binding.layFormTow.checkBalkon.isChecked) {
//                                binding.layFormTow.checkBalkon.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkSona = if (binding.layFormTow.checkSona.isChecked) {
//                                binding.layFormTow.checkSona.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkGazRomizi = if (binding.layFormTow.checkGazRomizi.isChecked) {
//                                binding.layFormTow.checkGazRomizi.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkHod = if (binding.layFormTow.checkHod.isChecked) {
//                                binding.layFormTow.checkHod.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkDarbZedSerghat =
//                                if (binding.layFormTow.checkDarbZedSerghat.isChecked) {
//                                    binding.layFormTow.checkDarbZedSerghat.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            checkKomodDivari = if (binding.layFormTow.checkKomodDivari.isChecked) {
//                                binding.layFormTow.checkKomodDivari.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkHayat = if (binding.layFormTow.checkHayat.isChecked) {
//                                binding.layFormTow.checkHayat.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkParking = if (binding.layFormTow.checkParking.isChecked) {
//                                binding.layFormTow.checkParking.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkJakozi = if (binding.layFormTow.checkJakozi.isChecked) {
//                                binding.layFormTow.checkJakozi.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkEstakhr = if (binding.layFormTow.checkEstakhr.isChecked) {
//                                binding.layFormTow.checkEstakhr.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkIphonTasviri =
//                                if (binding.layFormTow.checkIphonTasviri.isChecked) {
//                                    binding.layFormTow.checkIphonTasviri.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            checkLabi = if (binding.layFormTow.checkLabi.isChecked) {
//                                binding.layFormTow.checkLabi.tag.toString()
//                            } else {
//                                getString(R.string.txt_does_not_have)
//                            }
//
//                            checkSalonVarzeshi =
//                                if (binding.layFormTow.checkSalonVarzeshi.isChecked) {
//                                    binding.layFormTow.checkSalonVarzeshi.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            checkSalonEjtemaat =
//                                if (binding.layFormTow.checkSalonEjtemaat.isChecked) {
//                                    binding.layFormTow.checkSalonEjtemaat.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            checkAntenMarkazi =
//                                if (binding.layFormTow.checkAntenMarkazi.isChecked) {
//                                    binding.layFormTow.checkAntenMarkazi.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            checkJarobarghiMarkazi =
//                                if (binding.layFormTow.checkJarobarghiMarkazi.isChecked) {
//                                    binding.layFormTow.checkJarobarghiMarkazi.tag.toString()
//                                } else {
//                                    getString(R.string.txt_does_not_have)
//                                }
//
//                            if (binding.layFormTow.checkSelectAll.isChecked) {
//                                checkAsansor = getString(R.string.txt_has_it)
//                                checkMelkBazsaziShode = getString(R.string.txt_has_it)
//                                checkAnbari = getString(R.string.txt_has_it)
//                                checkBalkon = getString(R.string.txt_has_it)
//                                checkSona = getString(R.string.txt_has_it)
//                                checkGazRomizi = getString(R.string.txt_has_it)
//                                checkHod = getString(R.string.txt_has_it)
//                                checkDarbZedSerghat = getString(R.string.txt_has_it)
//                                checkKomodDivari = getString(R.string.txt_has_it)
//                                checkHayat = getString(R.string.txt_has_it)
//                                checkParking = getString(R.string.txt_has_it)
//                                checkJakozi = getString(R.string.txt_has_it)
//                                checkEstakhr = getString(R.string.txt_has_it)
//                                checkIphonTasviri = getString(R.string.txt_has_it)
//                                checkLabi = getString(R.string.txt_has_it)
//                                checkSalonVarzeshi = getString(R.string.txt_has_it)
//                                checkSalonEjtemaat = getString(R.string.txt_has_it)
//                                checkAntenMarkazi = getString(R.string.txt_has_it)
//                                checkJarobarghiMarkazi = getString(R.string.txt_has_it)
//                                checkSelectAll = getString(R.string.txt_select_all)
//                            } else {
//                                getString(R.string.txt_not_selected)
//                            }
//
//                            val registerBuyAndSellFormTow = RegisterBuyAndSellModelFormTow(
//                                0,
//                                user,
//                                ownerName,
//                                ownerPhone,
//                                cabinets,
//                                addressFile,
//                                description,
//                                counterAllTabaghat,
//                                counterAllVahedha,
//                                counterVahedhaDarTabaghe,
//                                jahatSakhteman,
//                                jahatVahed,
//                                jensKaf,
//                                garmayesh,
//                                sarmayesh,
//                                typeUser,
//                                typeSanad,
//                                metrazhMoraba,
//                                location,
//                                ageBana,
//                                tabaghe,
//                                cunterOtagh,
//                                vaziyatMelk,
//                                sureVame,
//                                priceMelk,
//                                wC,
//                                nemaSakhteman,
//                                checkAsansor,
//                                checkMelkBazsaziShode,
//                                checkAnbari,
//                                checkParking,
//                                checkEstakhr,
//                                checkIphonTasviri,
//                                checkBalkon,
//                                checkSona,
//                                checkGazRomizi,
//                                checkHod,
//                                checkDarbZedSerghat,
//                                checkKomodDivari,
//                                checkHayat,
//                                checkJakozi,
//                                checkLabi,
//                                checkSalonVarzeshi,
//                                checkSalonEjtemaat,
//                                checkAntenMarkazi,
//                                checkJarobarghiMarkazi,
//                                checkSelectAll,
//                                photo1,
//                                photo1,
//                                photo1,
//                                photo1,
//                                photo1,
//                                photo1
//                            )
//
//                            val result = db.dBDao()
//                                .upsertRegisterBuyAndSellFormTow(registerBuyAndSellFormTow)
//                            if (result > 0) {
//                                showDialogDoYouContinue(requireActivity())
////                        agreeDialog()
//                            } else {
//                                Toast.makeText(
//                                    requireActivity(),
//                                    getString(R.string.txt_save_infonmation_failure),
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            R.id.r_b_form_one -> {
//                checkForms = 1
//                binding.layFormOne.layOne.visibility = View.VISIBLE
//                binding.layFormTow.layTow.visibility = View.GONE
//                itemsEmptyForms()
//                validationDisabled()
//            }
//
//            R.id.r_b_form_tow -> {
//                checkForms = 2
//                binding.layFormOne.layOne.visibility = View.GONE
//                binding.layFormTow.layTow.visibility = View.VISIBLE
//                validationDisabled()
//                itemsEmptyForms()
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == cameraRequest) {
//            val photo: Bitmap = data?.extras?.get("data") as Bitmap
//
//            when (cameraRequest) {
//                1 -> {
//                    binding.layFormTow.img1.visibility = View.GONE
//                    binding.layFormTow.txtImg1.visibility = View.GONE
//                    binding.layFormTow.image1.visibility = View.VISIBLE
//                    binding.layFormTow.btnImgDelete1.visibility = View.VISIBLE
//                    binding.layFormTow.image1.setImageBitmap(photo)
//                    photo1 = bitMapToString(photo)
//                }
//
//                2 -> {
//                    binding.layFormTow.img2.visibility = View.GONE
//                    binding.layFormTow.txtImg2.visibility = View.GONE
//                    binding.layFormTow.image2.visibility = View.VISIBLE
//                    binding.layFormTow.btnImgDelete2.visibility = View.VISIBLE
//                    binding.layFormTow.image2.setImageBitmap(photo)
//                    photo2 = bitMapToString(photo)
//                }
//
//                3 -> {
//                    binding.layFormTow.img3.visibility = View.GONE
//                    binding.layFormTow.txtImg3.visibility = View.GONE
//                    binding.layFormTow.image3.visibility = View.VISIBLE
//                    binding.layFormTow.btnImgDelete3.visibility = View.VISIBLE
//                    binding.layFormTow.image3.setImageBitmap(photo)
//                    photo3 = bitMapToString(photo)
//                }
//
//                4 -> {
//                    binding.layFormTow.img4.visibility = View.GONE
//                    binding.layFormTow.txtImg4.visibility = View.GONE
//                    binding.layFormTow.image4.visibility = View.VISIBLE
//                    binding.layFormTow.btnImgDelete4.visibility = View.VISIBLE
//                    binding.layFormTow.image4.setImageBitmap(photo)
//                    photo4 = bitMapToString(photo)
//                }
//
//                5 -> {
//                    binding.layFormTow.img5.visibility = View.GONE
//                    binding.layFormTow.txtImg5.visibility = View.GONE
//                    binding.layFormTow.image5.visibility = View.VISIBLE
//                    binding.layFormTow.btnImgDelete5.visibility = View.VISIBLE
//                    binding.layFormTow.image5.setImageBitmap(photo)
//                    photo5 = bitMapToString(photo)
//                }
//
//                6 -> {
//                    binding.layFormTow.img6.visibility = View.GONE
//                    binding.layFormTow.txtImg6.visibility = View.GONE
//                    binding.layFormTow.image6.visibility = View.VISIBLE
//                    binding.layFormTow.btnImgDelete6.visibility = View.VISIBLE
//                    binding.layFormTow.image6.setImageBitmap(photo)
//                    photo6 = bitMapToString(photo)
//                }
//            }
//
//        }
//
//    }
//
//
//    private fun agreeDialog() {
//        var alertDialogBuilder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
//        val li = LayoutInflater.from(context)
//        @SuppressLint("InflateParams") val promptsView: View =
//            li.inflate(R.layout.dialog_do_you_continue_layout, null)
//
//
//        promptsView.background = resources.getDrawable(R.color.transparent)
//        alertDialogBuilder.setView(promptsView)
//        alertDialogBuilder.setCancelable(true)
//        val alertDialog = alertDialogBuilder.create()
//
//        alertDialog.show()
//    }

//    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
//        var stringOfDate = year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth
//        val month: Int = monthOfYear + 1
//        val newmonth: String
//        val newday: String
//        newmonth = if (month < 10) {
//            "0$month"
//        } else {
//            month.toString()
//        }
//        newday = if (dayOfMonth < 10) {
//            "0$dayOfMonth"
//        } else {
//            dayOfMonth.toString()
//        }
//        stringOfDate = "$year/$newmonth/$newday"
//        binding.layFormTow.edtDate.setText("")
//        binding.layFormTow.edtDate.setText(stringOfDate)
//        binding.layFormOne.edtDate.setText("")
//        binding.layFormOne.edtDate.setText(stringOfDate)
//    }
//
//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        val hourString = if (hourOfDay < 10) "0$hourOfDay" else "" + hourOfDay
//        val minuteString = if (minute < 10) "0$minute" else "" + minute
//        val time = "$hourString:$minuteString"
//        binding.layFormTow.edtTime.setText("")
//        binding.layFormTow.edtTime.setText(time)
//        binding.layFormOne.edtTime.setText("")
//        binding.layFormOne.edtTime.setText(time)
//    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.r_b_form_one -> {
                counter = 1
                binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
                binding.rBFormOne.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormTow.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.txt_color_description_login))

                binding.txtBottom.text = "5"
                binding.circularProgressBar.progress = 20
            }

            R.id.r_b_form_tow -> {
                counter = 2
                binding.toolbar.txtTitle.text =
                    resources.getString(R.string.txt_register_all_jozeyat)
                binding.rBFormTow.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormOne.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.txt_color_description_login))

                binding.txtBottom.text = "8"
                binding.circularProgressBar.progress = 12.5.toInt()
            }

            R.id.btn_next123 -> {
                when(counter){
                    1 ->{
                        when (counterStepsOne) {
                            1 -> {
                                counterStepsOne = 1
                                if (binding.layFormOne.edtUserRegistering.text.toString() == "") {
                                    binding.layFormOne.layWarning1.visibility = View.VISIBLE
                                    binding.layFormOne.txtWarring1.text =
                                        resources.getString(R.string.txt_entery_user)
                                    binding.layFormOne.imgWarring1.animation = zAnim

                                } else {
                                    counterStepsOne = 2
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 40

                                    binding.layFormTwo.edtNameOwner.requestFocus()
                                    showKeyboard()

                                    binding.txtTop.text = "2"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_information_owner)
                                    binding.txtPageTitleBottom.text =
                                        " بعدی : ${resources.getString(R.string.txt_price_the_property)}"

                                    binding.img.background = resources.getDrawable(R.drawable.user2)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_information_owner)

                                    binding.btnPrevious.visibility = View.VISIBLE
                                }
                            }

                            2 -> {
                                counterStepsOne = 2
                                if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtFamilyOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else if (binding.layFormTwo.edtFamilyOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtFamilyOwner.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                } else if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else if (binding.layFormTwo.edtNameOwner.text.toString() == "") {
                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                } else if (binding.layFormTwo.edtFamilyOwner.text.toString() == "") {
                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                } else if (binding.layFormTwo.edtMobilePhoneNumber.text.toString() == "") {
                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else {

                                    val phoneNumber =
                                        binding.layFormTwo.edtMobilePhoneNumber.text.toString()
                                    if (!isValidPhoneNumber(phoneNumber)) {
                                        binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                        binding.layFormTwo.imgWarring3.animation = zAnim
                                        binding.layFormTwo.txtWarning3.text =
                                            "لطفاً شماره همراه معتبر وارد کنید"
                                    } else {
                                        binding.layFormTwo.layWarning3.visibility = View.GONE
                                        counterStepsOne = 3
                                        binding.layScroll.visibility = View.GONE
                                        binding.layScroll2.visibility = View.GONE
                                        binding.layScroll3.visibility = View.VISIBLE
                                        binding.circularProgressBar.progress = 60

                                        binding.layFormThree.edtPriceMelk.requestFocus()
                                        showKeyboard()

                                        binding.txtTop.text = "3"
                                        binding.txtPageTitleTop.text =
                                            resources.getString(R.string.txt_price_the_property)
                                        binding.txtPageTitleBottom.text =
                                            " بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                                        binding.img.background = resources.getDrawable(R.drawable.money_bag)
                                        binding.txtPageTitle.text =
                                            resources.getString(R.string.txt_price_the_property)

                                        binding.btnPrevious.visibility = View.VISIBLE
                                    }
                                }
                            }

                            3 -> {
                                counterStepsOne = 3
                                if (binding.layFormThree.edtPriceMelk.text.toString() == "") {
                                    binding.layFormThree.layWarning1.visibility = View.VISIBLE
                                    binding.layFormThree.imgWarring.animation = zAnim

                                } else {
                                    counterStepsOne = 4
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 80

                                    binding.layFormFour.edtAddressFile.requestFocus()
                                    showKeyboard()

                                    binding.txtTop.text = "4"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_address_and_metrazh)
                                    binding.txtPageTitleBottom.text =
                                        " بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                    binding.img.background = resources.getDrawable(R.drawable.map_point)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_address_and_metrazh)

                                }
                            }

                            4 -> {
                                counterStepsOne = 4
                                if (binding.layFormFour.edtAddressFile.text.toString() == "" &&
                                    binding.layFormFour.edtMetrazhMoraba.text.toString() == ""
                                ) {
                                    binding.layFormFour.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring.animation = zAnim
                                    binding.layFormFour.txtWarning1.text =
                                        resources.getString(R.string.txt_enter_address_melk)

                                    binding.layFormFour.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring2.animation = zAnim

                                } else if (binding.layFormFour.edtAddressFile.text.toString() == "") {
                                    binding.layFormFour.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring.animation = zAnim
                                    binding.layFormFour.txtWarning1.text =
                                        resources.getString(R.string.txt_enter_address_melk)

                                } else if (binding.layFormFour.edtMetrazhMoraba.text.toString() == "") {
                                    binding.layFormFour.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring2.animation = zAnim

                                } else {
                                    counterStepsOne = 5
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.GONE
                                    binding.layScroll5.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 100

                                    binding.btnNext123.text = resources.getString(R.string.txt_register2)

                                    binding.txtTop.text = "5"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                    binding.layDes2.visibility = View.GONE


                                    binding.img.background = resources.getDrawable(R.drawable.key_square)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                }
                            }

                            5 -> {
                                if (binding.layFormFive.radioBtn1.isChecked ||
                                    binding.layFormFive.radioBtn2.isChecked
                                ) {
                                    userRegistering = binding.layFormOne.edtUserRegistering.text.toString()
                                    DateRegistering = binding.layFormOne.edtDate.text.toString()
                                    TimeRegistering = binding.layFormOne.edtTime.text.toString()

                                    ownerName = binding.layFormTwo.edtNameOwner.text.toString()
                                    ownerFamily = binding.layFormTwo.edtFamilyOwner.text.toString()

                                    // الگوی شماره‌های مجاز برای ایرانسل، همراه اول و رایتل
                                    val validPhoneNumberRegex = "^(09[1-3][0-9][0-9]\\d{6})\$".toRegex()

                                    binding.layFormTwo.edtMobilePhoneNumber.addTextChangedListener { it ->
                                        val input = it.toString()
                                        if (input.isNotEmpty() && !validPhoneNumberRegex.matches(input)) {
                                            binding.layFormTwo.txtWarning3.text =
                                                "لطفاً شماره معتبر وارد کنید"
                                        }
                                    }

                                    ownerMobilePhone =
                                        binding.layFormTwo.edtMobilePhoneNumber.text.toString()

                                    priceMelk = binding.layFormThree.edtPriceMelk.text.toString() + " , " +
                                            binding.layFormThree.txtPrice.text.toString()

                                    addressFile = binding.layFormFour.edtAddressFile.text.toString()
                                    metrazhMoraba =
                                        binding.layFormFour.edtMetrazhMoraba.text.toString() + "متر"

                                    if (binding.layFormFive.radioBtn1.isChecked) {
                                        sureVame = binding.layFormFive.radioBtn1.tag.toString()
                                    } else if (binding.layFormFive.radioBtn2.isChecked) {
                                        sureVame = binding.layFormFive.radioBtn2.tag.toString()
                                    }
                                    description = binding.layFormFive.edtDescription.text.toString()

                                    val registerBuyAndSellFormOne = RegisterBuyAndSellModelFormOne(
                                        0,
                                        userRegistering,
                                        DateRegistering,
                                        TimeRegistering,
                                        ownerName,
                                        ownerFamily,
                                        ownerMobilePhone,
                                        priceMelk,
                                        addressFile,
                                        metrazhMoraba,
                                        sureVame,
                                        description
                                    )

                                    val result = db.dBDao()
                                        .upsertRegisterBuyAndSellFormOne(registerBuyAndSellFormOne)
                                    if (result > 0) {
                                        showDialogDoYouContinue(requireActivity())
                                    } else {
                                        Toast.makeText(
                                            requireActivity(),
                                            getString(R.string.txt_save_infonmation_failure),
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    binding.layFormFive.layWarning1.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                    2 ->{
                        when (counterStepsTwo) {
                            1 -> {
                                counterStepsTwo = 1
                                if (binding.layFormOne.edtUserRegistering.text.toString() == "") {
                                    binding.layFormOne.layWarning1.visibility = View.VISIBLE
                                    binding.layFormOne.txtWarring1.text =
                                        resources.getString(R.string.txt_entery_user)
                                    binding.layFormOne.imgWarring1.animation = zAnim

                                } else {
                                    counterStepsTwo = 2
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 25

                                    binding.layFormTwo.edtNameOwner.requestFocus()
                                    showKeyboard()

                                    binding.txtTop.text = "2"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_information_owner)
                                    binding.txtPageTitleBottom.text =
                                        " بعدی : ${resources.getString(R.string.txt_price_the_property)}"

                                    binding.img.background = resources.getDrawable(R.drawable.user2)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_information_owner)

                                    binding.btnPrevious.visibility = View.VISIBLE
                                }
                            }

                            2 -> {
                                counterStepsTwo = 2
                                if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtFamilyOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else if (binding.layFormTwo.edtFamilyOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtFamilyOwner.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                } else if (binding.layFormTwo.edtNameOwner.text.toString() == "" &&
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() == ""
                                ) {

                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else if (binding.layFormTwo.edtNameOwner.text.toString() == "") {
                                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring.animation = zAnim
                                    binding.layFormTwo.txtWarning1.text =
                                        resources.getString(R.string.txt_please_enter_name_owner)

                                } else if (binding.layFormTwo.edtFamilyOwner.text.toString() == "") {
                                    binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring2.animation = zAnim
                                    binding.layFormTwo.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_family_owner)

                                } else if (binding.layFormTwo.edtMobilePhoneNumber.text.toString() == "") {
                                    binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                    binding.layFormTwo.imgWarring3.animation = zAnim
                                    binding.layFormTwo.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_phone_number_owner)

                                } else {

                                    val phoneNumber =
                                        binding.layFormTwo.edtMobilePhoneNumber.text.toString()
                                    if (!isValidPhoneNumber(phoneNumber)) {
                                        binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                                        binding.layFormTwo.imgWarring3.animation = zAnim
                                        binding.layFormTwo.txtWarning3.text =
                                            "لطفاً شماره همراه معتبر وارد کنید"
                                    } else {
                                        binding.layFormTwo.layWarning3.visibility = View.GONE
                                        counterStepsTwo = 3
                                        binding.layScroll.visibility = View.GONE
                                        binding.layScroll2.visibility = View.GONE
                                        binding.layScroll3.visibility = View.VISIBLE
                                        binding.circularProgressBar.progress = 25

                                        binding.layFormThree.edtPriceMelk.requestFocus()
                                        showKeyboard()

                                        binding.txtTop.text = "3"
                                        binding.txtPageTitleTop.text =
                                            resources.getString(R.string.txt_price_the_property)
                                        binding.txtPageTitleBottom.text =
                                            " بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                                        binding.img.background = resources.getDrawable(R.drawable.money_bag)
                                        binding.txtPageTitle.text =
                                            resources.getString(R.string.txt_price_the_property)

                                        binding.btnPrevious.visibility = View.VISIBLE
                                    }
                                }
                            }

                            3 -> {
                                counterStepsTwo = 3
                                if (binding.layFormThree.edtPriceMelk.text.toString() == "") {
                                    binding.layFormThree.layWarning1.visibility = View.VISIBLE
                                    binding.layFormThree.imgWarring.animation = zAnim

                                } else {
                                    counterStepsTwo = 4
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 37.5.toInt()

                                    binding.layFormFour.edtAddressFile.requestFocus()
                                    showKeyboard()

                                    binding.txtTop.text = "4"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_address_and_metrazh)
                                    binding.txtPageTitleBottom.text =
                                        " بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                    binding.img.background = resources.getDrawable(R.drawable.map_point)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_address_and_metrazh)

                                }
                            }

                            4 -> {
                                counterStepsTwo = 4
                                if (binding.layFormFour.edtAddressFile.text.toString() == "" &&
                                    binding.layFormFour.edtMetrazhMoraba.text.toString() == ""
                                ) {
                                    binding.layFormFour.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring.animation = zAnim
                                    binding.layFormFour.txtWarning1.text =
                                        resources.getString(R.string.txt_enter_address_melk)

                                    binding.layFormFour.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring2.animation = zAnim

                                } else if (binding.layFormFour.edtAddressFile.text.toString() == "") {
                                    binding.layFormFour.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring.animation = zAnim
                                    binding.layFormFour.txtWarning1.text =
                                        resources.getString(R.string.txt_enter_address_melk)

                                } else if (binding.layFormFour.edtMetrazhMoraba.text.toString() == "") {
                                    binding.layFormFour.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring2.animation = zAnim

                                } else {
                                    counterStepsTwo = 5
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.GONE
                                    binding.layScroll55.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 50.toInt()


                                    binding.txtTop.text = "5"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                    binding.layDes2.visibility = View.GONE


                                    binding.img.background = resources.getDrawable(R.drawable.key_square)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                }
                            }

//                            5 -> {
//                                if (binding.layFormFive.radioBtn1.isChecked ||
//                                    binding.layFormFive.radioBtn2.isChecked
//                                ) {
//                                    userRegistering = binding.layFormOne.edtUserRegistering.text.toString()
//                                    DateRegistering = binding.layFormOne.edtDate.text.toString()
//                                    TimeRegistering = binding.layFormOne.edtTime.text.toString()
//
//                                    ownerName = binding.layFormTwo.edtNameOwner.text.toString()
//                                    ownerFamily = binding.layFormTwo.edtFamilyOwner.text.toString()
//
//                                    // الگوی شماره‌های مجاز برای ایرانسل، همراه اول و رایتل
//                                    val validPhoneNumberRegex = "^(09[1-3][0-9][0-9]\\d{6})\$".toRegex()
//
//                                    binding.layFormTwo.edtMobilePhoneNumber.addTextChangedListener { it ->
//                                        val input = it.toString()
//                                        if (input.isNotEmpty() && !validPhoneNumberRegex.matches(input)) {
//                                            binding.layFormTwo.txtWarning3.text =
//                                                "لطفاً شماره معتبر وارد کنید"
//                                        }
//                                    }
//
//                                    ownerMobilePhone =
//                                        binding.layFormTwo.edtMobilePhoneNumber.text.toString()
//
//                                    priceMelk = binding.layFormThree.edtPriceMelk.text.toString() + " , " +
//                                            binding.layFormThree.txtPrice.text.toString()
//
//                                    addressFile = binding.layFormFour.edtAddressFile.text.toString()
//                                    metrazhMoraba =
//                                        binding.layFormFour.edtMetrazhMoraba.text.toString() + "متر"
//
//                                    if (binding.layFormFive.radioBtn1.isChecked) {
//                                        sureVame = binding.layFormFive.radioBtn1.tag.toString()
//                                    } else if (binding.layFormFive.radioBtn2.isChecked) {
//                                        sureVame = binding.layFormFive.radioBtn2.tag.toString()
//                                    }
//                                    description = binding.layFormFive.edtDescription.text.toString()
//
//                                    val registerBuyAndSellFormOne = RegisterBuyAndSellModelFormOne(
//                                        0,
//                                        userRegistering,
//                                        DateRegistering,
//                                        TimeRegistering,
//                                        ownerName,
//                                        ownerFamily,
//                                        ownerMobilePhone,
//                                        priceMelk,
//                                        addressFile,
//                                        metrazhMoraba,
//                                        sureVame,
//                                        description
//                                    )
//
//                                    val result = db.dBDao()
//                                        .upsertRegisterBuyAndSellFormOne(registerBuyAndSellFormOne)
//                                    if (result > 0) {
//                                        showDialogDoYouContinue(requireActivity())
//                                    } else {
//                                        Toast.makeText(
//                                            requireActivity(),
//                                            getString(R.string.txt_save_infonmation_failure),
//                                            Toast.LENGTH_LONG
//                                        ).show()
//                                    }
//                                } else {
//                                    binding.layFormFive.layWarning1.visibility = View.VISIBLE
//                                }
//                            }

                            5 -> {
                                counterStepsTwo = 5
                                if (binding.layFormFiveFive.edtTypeUser.text.toString() == "" &&
                                    binding.layFormFiveFive.edtTypeSanad.text.toString() == ""&&
                                    binding.layFormFiveFive.edtLocation.text.toString() == ""&&
                                    binding.layFormFiveFive.edtTabaghe.text.toString() == ""&&
                                    binding.layFormFiveFive.edtAgeBana.text.toString() == ""&&
                                    binding.layFormFiveFive.edtVaziyadMelk.text.toString() == ""&&
                                    binding.layFormFiveFive.edtCunterOtagh.text.toString() == ""&&
                                    binding.layFormFiveFive.edtNemaSakhteman.text.toString() == ""&&
                                    binding.layFormFive.radioBtn1.isChecked ||
                                    binding.layFormFive.radioBtn2.isChecked

                                ) {
                                    binding.layFormFiveFive.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring.animation = zAnim
                                    binding.layFormFiveFive.txtWarring1.text =
                                        resources.getString(R.string.txt_enter_address_melk)

                                    binding.layFormFiveFive.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring2.animation = zAnim

                                } else if (binding.layFormFour.edtAddressFile.text.toString() == "") {
                                    binding.layFormFour.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring.animation = zAnim
                                    binding.layFormFour.txtWarning1.text =
                                        resources.getString(R.string.txt_enter_address_melk)

                                } else if (binding.layFormFour.edtMetrazhMoraba.text.toString() == "") {
                                    binding.layFormFour.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFour.imgWarring2.animation = zAnim

                                } else {
                                    counterStepsTwo = 6
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.GONE
                                    binding.layScroll5.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 62.5.toInt()


                                    binding.txtTop.text = "5"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                    binding.layDes2.visibility = View.GONE


                                    binding.img.background = resources.getDrawable(R.drawable.key_square)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                }
                            }
                        }
                    }
                }


            }

            R.id.btn_previous -> {
                when(counter){
                    1 ->{
                        when (counterStepsOne) {
//                    1 ->{
//                        counterStepsOne = 1
//                        binding.layScroll.visibility = View.VISIBLE
//                        binding.layScroll2.visibility = View.GONE
//                        binding.circularProgressBar.progress = 20
//
//                        binding.txtTop.text = "1"
//                        binding.txtPageTitleTop.text = resources.getString(R.string.txt_info_paye_registering)
//                        binding.txtPageTitleBottom.text = resources.getString(R.string.txt_information_owner)
//
//                        binding.img.background = resources.getDrawable(R.drawable.document_text)
//                        binding.txtPageTitle.text = resources.getString(R.string.txt_info_paye_registering)
//
//                        binding.btnPrevious.visibility = View.GONE
//                    }
                            2 -> {
                                counterStepsOne = 1
                                binding.layScroll.visibility = View.VISIBLE
                                binding.layScroll2.visibility = View.GONE
                                binding.circularProgressBar.progress = 20

                                binding.txtTop.text = "1"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_info_paye_registering)
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_information_owner)}"


                                binding.img.background = resources.getDrawable(R.drawable.document_text)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_info_paye_registering)

                                binding.btnPrevious.visibility = View.GONE

                            }

                            3 -> {
                                counterStepsOne = 2
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.VISIBLE
                                binding.layScroll3.visibility = View.GONE
                                binding.circularProgressBar.progress = 40

                                binding.txtTop.text = "2"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_information_owner)
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_price_the_property)}"


                                binding.img.background = resources.getDrawable(R.drawable.user2)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_information_owner)
                            }

                            4 -> {
                                counterStepsOne = 3
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.VISIBLE
                                binding.layScroll4.visibility = View.GONE
                                binding.circularProgressBar.progress = 60

                                binding.txtTop.text = "3"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_price_the_property)
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                                binding.img.background = resources.getDrawable(R.drawable.money_bag)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_price_the_property)
                            }

                            5 -> {
                                counterStepsOne = 4
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.GONE
                                binding.layScroll4.visibility = View.VISIBLE
                                binding.layScroll5.visibility = View.GONE
                                binding.circularProgressBar.progress = 80

                                binding.txtTop.text = "4"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_address_and_metrazh)
                                binding.layDes2.visibility = View.VISIBLE
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                binding.img.background = resources.getDrawable(R.drawable.map_point)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_price_the_property)
                            }
                        }
                    }
                    2 ->{
                        when (counterStepsTwo) {
//                    1 ->{
//                        counterStepsOne = 1
//                        binding.layScroll.visibility = View.VISIBLE
//                        binding.layScroll2.visibility = View.GONE
//                        binding.circularProgressBar.progress = 20
//
//                        binding.txtTop.text = "1"
//                        binding.txtPageTitleTop.text = resources.getString(R.string.txt_info_paye_registering)
//                        binding.txtPageTitleBottom.text = resources.getString(R.string.txt_information_owner)
//
//                        binding.img.background = resources.getDrawable(R.drawable.document_text)
//                        binding.txtPageTitle.text = resources.getString(R.string.txt_info_paye_registering)
//
//                        binding.btnPrevious.visibility = View.GONE
//                    }
                            2 -> {
                                counterStepsTwo = 1
                                binding.layScroll.visibility = View.VISIBLE
                                binding.layScroll2.visibility = View.GONE
                                binding.circularProgressBar.progress = 12.5.toInt()

                                binding.txtTop.text = "1"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_info_paye_registering)
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_information_owner)}"


                                binding.img.background = resources.getDrawable(R.drawable.document_text)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_info_paye_registering)

                                binding.btnPrevious.visibility = View.GONE

                            }

                            3 -> {
                                counterStepsTwo = 2
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.VISIBLE
                                binding.layScroll3.visibility = View.GONE
                                binding.circularProgressBar.progress = 25

                                binding.txtTop.text = "2"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_information_owner)
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_price_the_property)}"


                                binding.img.background = resources.getDrawable(R.drawable.user2)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_information_owner)
                            }

                            4 -> {
                                counterStepsTwo = 3
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.VISIBLE
                                binding.layScroll4.visibility = View.GONE
                                binding.layScroll55.visibility = View.GONE
                                binding.circularProgressBar.progress = 37.5.toInt()

                                binding.txtTop.text = "3"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_price_the_property)
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                                binding.img.background = resources.getDrawable(R.drawable.money_bag)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_price_the_property)
                            }

                            5 -> {
                                counterStepsTwo = 4
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.GONE
                                binding.layScroll4.visibility = View.VISIBLE
                                binding.layScroll55.visibility = View.GONE
                                binding.circularProgressBar.progress = 50

                                binding.txtTop.text = "4"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_address_and_metrazh)
                                binding.layDes2.visibility = View.VISIBLE
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                binding.img.background = resources.getDrawable(R.drawable.map_point)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_price_the_property)
                            }

                            6 -> {
                                counterStepsTwo = 5
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.GONE
                                binding.layScroll4.visibility = View.GONE
                                binding.layScroll55.visibility = View.VISIBLE
                                binding.circularProgressBar.progress = 62.5.toInt()

                                binding.txtTop.text = "5"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_address_and_metrazh)
                                binding.layDes2.visibility = View.VISIBLE
                                binding.txtPageTitleBottom.text =
                                    " بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                binding.img.background = resources.getDrawable(R.drawable.map_point)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_price_the_property)
                            }
                        }
                    }
                }



            }

            R.id.radio_btn1 -> {
                binding.layFormFive.radioBtn1.isChecked = true
                binding.layFormFive.radioBtn2.isChecked = false
                binding.layFormFive.layWarning1.visibility = View.GONE
                binding.layFormFive.layRadioBtn1.background =
                    resources.getDrawable(R.drawable.border9)
                binding.layFormFive.layRadioBtn2.background =
                    resources.getDrawable(R.drawable.border4)
            }

            R.id.radio_btn2 -> {
                binding.layFormFive.radioBtn1.isChecked = false
                binding.layFormFive.radioBtn2.isChecked = true
                binding.layFormFive.layWarning1.visibility = View.GONE
                binding.layFormFive.layRadioBtn1.background =
                    resources.getDrawable(R.drawable.border4)
                binding.layFormFive.layRadioBtn2.background =
                    resources.getDrawable(R.drawable.border9)
            }
        }
    }

    @SuppressLint("SetTextI18n")

    private fun textWatchers() {
        // Todo Text Watcher Step One
        binding.layFormOne.edtUserRegistering.addTextChangedListener {

            if (binding.layFormOne.edtUserRegistering.text.toString() != "" &&
                binding.layFormOne.edtUserRegistering.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormOne.edtUserRegistering.text.toString()).find()) {
                    binding.layFormOne.layWarning1.visibility = View.VISIBLE
                    binding.layFormOne.txtWarring1.text =
                        resources.getString(R.string.txt_please_enter_lango_farsi)
                    binding.layFormOne.edtUserRegistering.setText("")

                } else {
                    binding.layFormOne.layWarning1.visibility = View.GONE
                }
            }
        }
        binding.layFormOne.edtDate.addTextChangedListener {
            if (binding.layFormOne.edtDate.text.toString() != "") {
                binding.layFormOne.layWarning2.visibility = View.GONE
            }
        }
        binding.layFormOne.edtTime.addTextChangedListener {
            if (binding.layFormOne.edtTime.text.toString() != "") {
                binding.layFormOne.layWarning3.visibility = View.GONE
            }
        }


        // Todo Text Watcher Step Two
        binding.layFormTwo.edtNameOwner.addTextChangedListener {
            if (binding.layFormTwo.edtNameOwner.text.toString() != "" &&
                binding.layFormTwo.edtNameOwner.text.toString() != " "
            ) {

                if (pattern.matcher(binding.layFormTwo.edtNameOwner.text.toString()).find()) {
                    binding.layFormTwo.layWarning1.visibility = View.VISIBLE
                    binding.layFormTwo.txtWarning1.text =
                        resources.getString(R.string.txt_please_enter_lango_farsi)
                    binding.layFormTwo.edtNameOwner.setText("")

                } else {
                    binding.layFormTwo.layWarning1.visibility = View.GONE
                }
            }
            binding.layFormTwo.edtFamilyOwner.addTextChangedListener {
                if (binding.layFormTwo.edtFamilyOwner.text.toString() != "" &&
                    binding.layFormTwo.edtFamilyOwner.text.toString() != " "
                ) {
                    if (pattern.matcher(binding.layFormTwo.edtFamilyOwner.text.toString()).find()) {
                        binding.layFormTwo.layWarning2.visibility = View.VISIBLE
                        binding.layFormTwo.txtWarring2.text =
                            resources.getString(R.string.txt_please_enter_lango_farsi)
                        binding.layFormTwo.edtFamilyOwner.setText("")

                    } else {
                        binding.layFormTwo.layWarning2.visibility = View.GONE
                    }
                }
            }

            binding.layFormTwo.edtMobilePhoneNumber.addTextChangedListener {
                if (binding.layFormTwo.edtMobilePhoneNumber.text.toString() != "" &&
                    binding.layFormTwo.edtMobilePhoneNumber.text.toString() != " "
                ) {
                    if (pattern.matcher(binding.layFormTwo.edtMobilePhoneNumber.text.toString())
                            .find()
                    ) {
                        binding.layFormTwo.layWarning3.visibility = View.VISIBLE
                        binding.layFormTwo.txtWarning3.text =
                            resources.getString(R.string.txt_please_enter_lango_farsi)
                        binding.layFormTwo.edtMobilePhoneNumber.setText("")

                    } else {
                        binding.layFormTwo.edtMobilePhoneNumber.letterSpacing = 1F
                        binding.layFormTwo.layWarning3.visibility = View.GONE
                    }
                }
            }


            // Todo Text Watcher Step Three
            binding.layFormThree.edtPriceMelk.addTextChangedListener {
                if (binding.layFormThree.edtPriceMelk.text.toString() != "" &&
                    binding.layFormThree.edtPriceMelk.text.toString() != " "
                ) {
                    binding.layFormThree.layWarning1.visibility = View.GONE
                    NumberTextWatcher(binding.layFormThree.edtPriceMelk)
                }
            }
            binding.layFormThree.edtPriceMelk.addTextChangedListener(
                NumberTextWatcher(binding.layFormThree.edtPriceMelk)
            )
            binding.layFormThree.edtPriceMelk.doAfterTextChanged {
                if (binding.layFormThree.edtPriceMelk.length() == 0) {
                    binding.layFormThree.txtPrice.visibility = View.GONE
                } else if (binding.layFormThree.edtPriceMelk.length() != 0) {
                    binding.layFormThree.txtPrice.visibility = View.VISIBLE
                    val number = it.toString()
                    binding.layFormThree.txtPrice.text =
                        PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
                }
            }


            // Todo Text Watcher Step Four
            binding.layFormFour.edtAddressFile.addTextChangedListener {
                if (binding.layFormFour.edtAddressFile.text.toString() != "" &&
                    binding.layFormFour.edtAddressFile.text.toString() != " "
                ) {
                    if (pattern.matcher(binding.layFormFour.edtAddressFile.text.toString())
                            .find()
                    ) {
                        binding.layFormFour.layWarning1.visibility = View.VISIBLE
                        binding.layFormFour.txtWarning1.text =
                            resources.getString(R.string.txt_please_enter_lango_farsi)
                        binding.layFormFour.edtAddressFile.setText("")

                    } else {
                        binding.layFormFour.layWarning1.visibility = View.GONE
                    }

                }
            }
            binding.layFormFour.edtMetrazhMoraba.addTextChangedListener {
                if (binding.layFormFour.edtMetrazhMoraba.text.toString() != "" &&
                    binding.layFormFour.edtMetrazhMoraba.text.toString() != ""
                ) {
                    binding.layFormFour.layWarning2.visibility = View.GONE
                }
            }


            // Todo Text Watcher Step Five
            binding.layFormFive.edtDescription.addTextChangedListener {
                if (pattern.matcher(binding.layFormFive.edtDescription.text.toString()).find()) {
                    binding.layFormFive.layWarning2.visibility = View.VISIBLE
                    binding.layFormFive.imgWarring2.animation = zAnim
                    binding.layFormFive.txtWarning2.text =
                        resources.getString(R.string.txt_please_enter_lango_farsi)
                    binding.layFormFive.edtDescription.setText("")

                } else {
                    binding.layFormFive.layWarning2.visibility = View.GONE
                }


            }

        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val irancellPattern = Regex("^09[0-3]\\d{8}$")  // برای پیش‌شماره‌های ایرانسل
        val mciPattern =
            Regex("^09(1\\\\d{8}|99\\\\d{7})\$")        // برای پیش‌شماره‌های همراه اول
        val rightelPattern = Regex("^092\\d{8}$")  // برای پیش‌شماره‌های رایتل

        val invalidNumbers = listOf("09300000000", "09100000000", "09900000000", "09200000000")

        return phoneNumber !in invalidNumbers && (phoneNumber.matches(irancellPattern) ||
                phoneNumber.matches(mciPattern) || phoneNumber.matches(rightelPattern))
    }

    private fun itemsEmptyForms() {
        when (checkForms) {
            1 -> {
                counterStepsOne = 1
                binding.layFormOne.edtUserRegistering.setText("")

                binding.layFormTwo.edtNameOwner.setText("")
                binding.layFormTwo.edtFamilyOwner.setText("")
                binding.layFormTwo.edtMobilePhoneNumber.setText("")

                binding.layFormThree.edtPriceMelk.setText("")

                binding.layFormFour.edtAddressFile.setText("")
                binding.layFormFour.edtMetrazhMoraba.setText("")

                binding.layFormFive.radioBtn1.isChecked = false
                binding.layFormFive.radioBtn2.isChecked = false
                binding.layFormFive.edtDescription.setText("")


                binding.layScroll.visibility = View.VISIBLE
                binding.layScroll5.visibility = View.GONE
                binding.layScroll4.visibility = View.GONE
                binding.layScroll3.visibility = View.GONE
                binding.layScroll2.visibility = View.GONE

                binding.circularProgressBar.progress = 20
                binding.txtTop.text = "1"
                binding.txtPageTitleTop.text =
                    resources.getString(R.string.txt_info_paye_registering)
                binding.txtPageTitleBottom.text =
                    " بعدی : ${resources.getString(R.string.txt_information_owner)}"
                binding.layDes2.visibility = View.VISIBLE
                binding.img.background = resources.getDrawable(R.drawable.document_text)
                binding.txtPageTitle.text =
                    resources.getString(R.string.txt_info_paye_registering)

                binding.btnPrevious.visibility = View.GONE

            }

//            2 -> {
//                binding.layFormTow.edtUserRegistering.setText("")
//                binding.layFormTow.edtOwnerName.setText("")
//                binding.layFormTow.edtOwnerPhone.setText("")
//                binding.layFormTow.edtCabinets.setText("")
//                binding.layFormTow.edtJahatSakhteman.setText("")
//                binding.layFormTow.edtJahatVahed.setText("")
//                binding.layFormTow.edtJensKaf.setText("")
//                binding.layFormTow.edtGarmayesh.setText("")
//                binding.layFormTow.edtSarmayesh.setText("")
//                binding.layFormTow.edtAddressFile.setText("")
//                binding.layFormTow.edtDescription.setText("")
//                binding.layFormTow.edtCounterAllTabaghat.setText("")
//                binding.layFormTow.edtCounterAllVahedha.setText("")
//                binding.layFormTow.edtCounterVahedhaDarTabaghe.setText("")
//                binding.layFormTow.edtTypeUser.setText("")
//                binding.layFormTow.edtTypeSanad.setText("")
//                binding.layFormTow.edtMetrazhMoraba.setText("")
//                binding.layFormTow.edtLocation.setText("")
//                binding.layFormTow.edtAgeBana.setText("")
//                binding.layFormTow.edtTabaghe.setText("")
//                binding.layFormTow.edtCunterOtagh.setText("")
//                binding.layFormTow.edtVaziyadMelk.setText("")
//                binding.layFormTow.edtPriceMelk.setText("")
//                binding.layFormTow.edtWc.setText("")
//                binding.layFormTow.edtNemaSakhteman.setText("")
//
//                binding.layFormTow.checkSelectAll.isChecked = false
//                binding.layFormTow.checkAnbari.isChecked = false
//                binding.layFormTow.checkAsansor.isChecked = false
//                binding.layFormTow.checkBalkon.isChecked = false
//                binding.layFormTow.checkParking.isChecked = false
//                binding.layFormTow.checkIphonTasviri.isChecked = false
//                binding.layFormTow.checkEstakhr.isChecked = false
//                binding.layFormTow.checkSona.isChecked = false
//                binding.layFormTow.checkGazRomizi.isChecked = false
//                binding.layFormTow.checkHod.isChecked = false
//                binding.layFormTow.checkKomodDivari.isChecked = false
//                binding.layFormTow.checkDarbZedSerghat.isChecked = false
//                binding.layFormTow.checkHayat.isChecked = false
//                binding.layFormTow.checkJakozi.isChecked = false
//                binding.layFormTow.checkLabi.isChecked = false
//                binding.layFormTow.checkSalonVarzeshi.isChecked = false
//                binding.layFormTow.checkSalonEjtemaat.isChecked = false
//                binding.layFormTow.checkAntenMarkazi.isChecked = false
//                binding.layFormTow.checkJarobarghiMarkazi.isChecked = false
//                binding.layFormTow.checkMelkBazsaziShode.isChecked = false
//
//                binding.layFormTow.img1.visibility = View.VISIBLE
//                binding.layFormTow.txtImg1.visibility = View.VISIBLE
//                binding.layFormTow.image1.visibility = View.GONE
//                binding.layFormTow.btnImgDelete1.visibility = View.GONE
//                binding.layFormTow.image1.setImageBitmap(null)
//
//                binding.layFormTow.img2.visibility = View.VISIBLE
//                binding.layFormTow.txtImg2.visibility = View.VISIBLE
//                binding.layFormTow.image2.visibility = View.GONE
//                binding.layFormTow.btnImgDelete2.visibility = View.GONE
//                binding.layFormTow.image2.setImageBitmap(null)
//
//                binding.layFormTow.img3.visibility = View.VISIBLE
//                binding.layFormTow.txtImg3.visibility = View.VISIBLE
//                binding.layFormTow.image3.visibility = View.GONE
//                binding.layFormTow.btnImgDelete3.visibility = View.GONE
//                binding.layFormTow.image3.setImageBitmap(null)
//
//                binding.layFormTow.img4.visibility = View.VISIBLE
//                binding.layFormTow.txtImg4.visibility = View.VISIBLE
//                binding.layFormTow.image4.visibility = View.GONE
//                binding.layFormTow.btnImgDelete4.visibility = View.GONE
//                binding.layFormTow.image4.setImageBitmap(null)
//
//                binding.layFormTow.img5.visibility = View.VISIBLE
//                binding.layFormTow.txtImg5.visibility = View.VISIBLE
//                binding.layFormTow.image5.visibility = View.GONE
//                binding.layFormTow.btnImgDelete5.visibility = View.GONE
//                binding.layFormTow.image5.setImageBitmap(null)
//
//                binding.layFormTow.img6.visibility = View.VISIBLE
//                binding.layFormTow.txtImg6.visibility = View.VISIBLE
//                binding.layFormTow.image6.visibility = View.GONE
//                binding.layFormTow.btnImgDelete6.visibility = View.GONE
//                binding.layFormTow.image6.setImageBitmap(null)
//            }
        }


    }

//    private val homeMvvm: RegisterBuyAndSellViewModel by viewModels()
//    private lateinit var owner: LifecycleOwner
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        this.owner = this
//    }
//    private fun getConvertLocInAddress() {
//        homeMvvm.getConvertLocToAddress()
//        homeMvvm.getConvertLocToAddress.observe(viewLifecycleOwner) { data ->
//
//            binding.layFormFour.edtAddressFile.text = da
//        }
//
//    }
}
