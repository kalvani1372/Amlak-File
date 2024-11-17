package com.dev.amr.amlakfile.ui.fragment.nav_main_fragment.btmsheet_items_add.register_buy_sell_fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.Navigation
import androidx.room.Room
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.base.BaseFragment
import com.dev.amr.amlakfile.base.BaseLiveDialog
import com.dev.amr.amlakfile.data.db.DBRoom
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormOne
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormTwo
import com.dev.amr.amlakfile.databinding.ActivityTestMainBinding
import com.dev.amr.amlakfile.ui.btmSheetDialog.BtmSheetKharidForoshDialog
import com.dev.amr.amlakfile.ui.btmSheetDialog.btmSheetStep.BtmSheetStepDialog
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
    private lateinit var priceMelk: String
    private lateinit var addressFile: String
    private lateinit var metrazhMoraba: String
    private lateinit var typeUser: String
    private lateinit var typeSanad: String
    private lateinit var location: String
    private lateinit var tabaghe: String
    private lateinit var ageBana: String
    private lateinit var vaziyatMelk: String
    private lateinit var cunterOtagh: String
    private lateinit var nemaSakhteman: String
    private lateinit var sureVame: String
    private lateinit var description: String
    private lateinit var counterAllTabaghat: String
    private lateinit var counterAllVahedha: String
    private lateinit var counterVahedhaDarTabaghe: String
    private lateinit var jahatSakhteman: String
    private lateinit var jahatVahed: String
    private lateinit var jensKaf: String
    private lateinit var cabinets: String
    private lateinit var wC: String
    private lateinit var garmayesh: String
    private lateinit var sarmayesh: String
    private lateinit var checkAsansor: String
    private lateinit var checkParking: String
    private lateinit var checkMelkBazsaziShode: String
    private lateinit var checkJakozi: String
    private lateinit var checkAnbari: String
    private lateinit var checkEstakhr: String
    private lateinit var checkBalkon: String
    private lateinit var checkIphonTasviri: String
    private lateinit var checkSona: String
    private lateinit var checkLabi: String
    private lateinit var checkGazRomizi: String
    private lateinit var checkSalonVarzeshi: String
    private lateinit var checkHod: String
    private lateinit var checkSalonEjtemaat: String
    private lateinit var checkDarbZedSerghat: String
    private lateinit var checkAntenMarkazi: String
    private lateinit var checkKomodDivari: String
    private lateinit var checkJarobarghiMarkazi: String
    private lateinit var checkHayat: String
    private var checkSelectAll: String = ""

    private lateinit var zAnim: Animation
    val pattern = Pattern.compile("[a-zA-Z]+$")


    private var photo1: String = ""
    private var photo2: String = ""
    private var photo3: String = ""
    private var photo4: String = ""
    private var photo5: String = ""
    private var photo6: String = ""

    private var checkForms: Int = 1

    private var btmSheetKharidForosh: BtmSheetKharidForoshDialog = BtmSheetKharidForoshDialog()
    private var btmSheetStep: BtmSheetStepDialog = BtmSheetStepDialog()

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

        db = Room.databaseBuilder(requireActivity(), DBRoom::class.java, "amlak_db")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

        getCurrentDate(binding.layFormOne.edtDate, binding.layFormOne.edtTime)

        binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
        binding.layFormOne.edtUserRegistering.requestFocus()
        binding.toolbar.layLogo.visibility = View.GONE
        binding.toolbar.layBtnBack.visibility = View.VISIBLE
        textWatchers()
        onClickViews()
        binding.toolbar.btnBack.setOnClickListener(this)
        binding.rBFormOne.setOnClickListener(this)
        binding.rBFormTow.setOnClickListener(this)
        binding.btnNext123.setOnClickListener(this)
        binding.btnPrevious.setOnClickListener(this)
        binding.layFormFive.radioBtn1.setOnClickListener(this)
        binding.layFormFive.radioBtn2.setOnClickListener(this)
        binding.layFormFiveFive.radioBtn1Lay5.setOnClickListener(this)
        binding.layFormFiveFive.radioBtn2Lay5.setOnClickListener(this)

        BaseLiveDialog.liveDataEmptyItems.observe(requireActivity()) {
            itemsEmptyForms()
        }

        BaseLiveDialog.liveDataDoYouSure.observe(requireActivity()) {
            counterStepsTwo = 8
            binding.layScroll.visibility = View.GONE
            binding.layScroll2.visibility = View.GONE
            binding.layScroll3.visibility = View.GONE
            binding.layScroll4.visibility = View.GONE
            binding.layScroll55.visibility = View.GONE
            binding.layScroll6.visibility = View.GONE
            binding.layScroll7.visibility = View.GONE
            binding.layScroll8.visibility = View.VISIBLE
            binding.circularProgressBar.progress = 100


            binding.txtTop.text = "8"
            binding.txtPageTitleTop.text =
                resources.getString(R.string.txt_images)

            binding.layDes2.visibility = View.GONE

            binding.img.background =
                resources.getDrawable(R.drawable.camera)
            binding.txtPageTitle.text =
                resources.getString(R.string.txt_images)
        }

        BaseLiveDialog.liveDataBackToHomePage.observe(requireActivity()) {
            if (it != null) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_registerBuyAndSellFragment_to_mainFragment)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getCurrentDate(binding.layFormOne.edtDate, binding.layFormOne.edtTime)
    }

    private fun onClickViews() {
//        binding.toolbar.txtTitle.text = getString(R.string.txt_register_file_kharid_forosh)
//        binding.toolbar.btnMenu.visibility = View.GONE
//        binding.btnCancel.setOnClickListener(this)
//        binding.laySave.setOnClickListener(this)
        binding.btnEye.setOnClickListener(this)
        binding.layFormFiveFive.edtTypeUser.setOnClickListener(this)
        binding.layFormFiveFive.edtTypeSanad.setOnClickListener(this)
        binding.layFormFiveFive.edtAgeBana.setOnClickListener(this)
        binding.layFormFiveFive.edtTabaghe.setOnClickListener(this)
        binding.layFormFiveFive.edtCunterOtagh.setOnClickListener(this)
        binding.layFormFiveFive.edtVaziyadMelk.setOnClickListener(this)
        binding.layFormFiveFive.edtNemaSakhteman.setOnClickListener(this)
        binding.layFormSex.edtWc.setOnClickListener(this)
        binding.layFormSex.edtCabinets.setOnClickListener(this)
        binding.layFormSex.edtJahatSakhteman.setOnClickListener(this)
        binding.layFormSex.edtJahatVahed.setOnClickListener(this)
        binding.layFormSex.edtJensKaf.setOnClickListener(this)
        binding.layFormSex.edtGarmayesh.setOnClickListener(this)
        binding.layFormSex.edtSarmayesh.setOnClickListener(this)
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
        binding.layFormSeven.checkAsansor.setOnClickListener(this)
        binding.layFormSeven.checkParking.setOnClickListener(this)
        binding.layFormSeven.checkMelkBazsaziShode.setOnClickListener(this)
        binding.layFormSeven.checkJakozi.setOnClickListener(this)
        binding.layFormSeven.checkAnbari.setOnClickListener(this)
        binding.layFormSeven.checkEstakhr.setOnClickListener(this)
        binding.layFormSeven.checkBalkon.setOnClickListener(this)
        binding.layFormSeven.checkIphonTasviri.setOnClickListener(this)
        binding.layFormSeven.checkSona.setOnClickListener(this)
        binding.layFormSeven.checkLabi.setOnClickListener(this)
        binding.layFormSeven.checkGazRomizi.setOnClickListener(this)
        binding.layFormSeven.checkSalonVarzeshi.setOnClickListener(this)
        binding.layFormSeven.checkHod.setOnClickListener(this)
        binding.layFormSeven.checkSalonEjtemaat.setOnClickListener(this)
        binding.layFormSeven.checkDarbZedSerghat.setOnClickListener(this)
        binding.layFormSeven.checkAntenMarkazi.setOnClickListener(this)
        binding.layFormSeven.checkKomodDivari.setOnClickListener(this)
        binding.layFormSeven.checkJarobarghiMarkazi.setOnClickListener(this)
        binding.layFormSeven.checkHayat.setOnClickListener(this)
        binding.layFormSeven.checkSelectAll.setOnClickListener(this)
        binding.layFormEghte.layUploadImage.setOnClickListener(this)
        binding.layFormEghte.imgUploadImage.setOnClickListener(this)
        binding.layFormEghte.txtUploadImage.setOnClickListener(this)
//        binding.layFormTow.btnShowItems.setOnClickListener(this)
//        binding.layFormTow.imgShowItems.setOnClickListener(this)
//        binding.rBFormOne.setOnClickListener(this)
//        binding.rBFormTow.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BaseLiveDialog.liveDataTypeUser.observe(requireActivity()) {
            try {
                when (Hawks.getInt("countGetList")) {
                    1 -> {
                        binding.layFormFiveFive.edtTypeUser.error = null
                        binding.layFormFiveFive.edtTypeUser.setText(Hawks.getString("item"))
                    }

                    2 -> {
                        binding.layFormFiveFive.edtTypeSanad.error = null
                        binding.layFormFiveFive.edtTypeSanad.setText(Hawks.getString("item"))
                    }

                    3 -> {
                        binding.layFormFiveFive.edtAgeBana.error = null
                        binding.layFormFiveFive.edtAgeBana.setText(Hawks.getString("item"))
                    }

                    4 -> {
                        binding.layFormFiveFive.edtTabaghe.error = null
                        binding.layFormFiveFive.edtTabaghe.setText(Hawks.getString("item"))
                    }

                    5 -> {
                        binding.layFormFiveFive.edtCunterOtagh.error = null
                        binding.layFormFiveFive.edtCunterOtagh.setText(Hawks.getString("item"))
                    }

                    6 -> {
                        binding.layFormFiveFive.edtVaziyadMelk.error = null
                        binding.layFormFiveFive.edtVaziyadMelk.setText(Hawks.getString("item"))
                    }

                    7 -> {
                        binding.layFormSex.edtWc.error = null
                        binding.layFormSex.edtWc.setText(Hawks.getString("item"))
                        binding.layFormSex.edtWc.requestFocus()
                    }

                    8 -> {
                        binding.layFormFiveFive.edtNemaSakhteman.error = null
                        binding.layFormFiveFive.edtNemaSakhteman.setText(Hawks.getString("item"))
                    }

                    9 -> {
                        binding.layFormSex.edtCabinets.error = null
                        binding.layFormSex.edtCabinets.setText(Hawks.getString("item"))
                        binding.layFormSex.edtCabinets.requestFocus()
                    }

                    10 -> {
                        binding.layFormSex.edtJahatSakhteman.error = null
                        binding.layFormSex.edtJahatSakhteman.setText(Hawks.getString("item"))
                        binding.layFormSex.edtJahatSakhteman.requestFocus()
                    }

                    11 -> {
                        binding.layFormSex.edtJahatVahed.error = null
                        binding.layFormSex.edtJahatVahed.setText(Hawks.getString("item"))
                        binding.layFormSex.edtJahatVahed.requestFocus()
                    }

                    12 -> {
                        binding.layFormSex.edtJensKaf.error = null
                        binding.layFormSex.edtJensKaf.setText(Hawks.getString("item"))
                        binding.layFormSex.edtJensKaf.requestFocus()
                    }

                    13 -> {
                        binding.layFormSex.edtGarmayesh.error = null
                        binding.layFormSex.edtGarmayesh.setText(Hawks.getString("item"))
                        binding.layFormSex.edtGarmayesh.requestFocus()
                    }

                    14 -> {
                        binding.layFormSex.edtSarmayesh.error = null
                        binding.layFormSex.edtSarmayesh.setText(Hawks.getString("item"))
                        binding.layFormSex.edtSarmayesh.requestFocus()
                    }
                }

                btmSheetKharidForosh.dismiss()
            } catch (e: Exception) {
                e.message
            }
        }
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
            R.id.btn_back -> {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_registerBuyAndSellFragment_to_mainFragment)
            }

            R.id.r_b_form_one -> {
                counter = 1
                checkForms = 1
                binding.toolbar.txtTitle.text = resources.getString(R.string.txt_register_kharid)
                binding.rBFormOne.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormTow.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.txt_color_description_login))

                binding.txtBottom.text = "5"
                binding.circularProgressBar.progress = 20
                itemsEmptyForms()
            }

            R.id.r_b_form_tow -> {
                counter = 2
                checkForms = 2
                binding.toolbar.txtTitle.text =
                    resources.getString(R.string.txt_register_all_jozeyat)
                binding.rBFormTow.background = resources.getDrawable(R.drawable.border7)
                binding.rBFormTow.setTextColor(resources.getColor(R.color.color_btn_login))

                binding.rBFormOne.background = resources.getDrawable(R.drawable.border6)
                binding.rBFormOne.setTextColor(resources.getColor(R.color.txt_color_description_login))

                binding.txtBottom.text = "8"
                binding.circularProgressBar.progress = 12.5.toInt()
                itemsEmptyForms()
            }

            R.id.btn_eye -> {
                btmSheetStep.show(childFragmentManager, "btmSheetStep")
//                btmSheetStep.isCancelable = false
            }

            R.id.btn_next123 -> {
                when (counter) {
                    1 -> {
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
                                        "بعدی : ${resources.getString(R.string.txt_price_the_property)}"

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
                                            "بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                                        binding.img.background =
                                            resources.getDrawable(R.drawable.money_bag)
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
                                        "بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                    binding.img.background =
                                        resources.getDrawable(R.drawable.map_point)
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

                                    binding.btnNext123.text =
                                        resources.getString(R.string.txt_register2)

                                    binding.txtTop.text = "5"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                    binding.layDes2.visibility = View.GONE


                                    binding.img.background =
                                        resources.getDrawable(R.drawable.key_square)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                }
                            }

                            5 -> {
                                if (binding.layFormFive.radioBtn1.isChecked ||
                                    binding.layFormFive.radioBtn2.isChecked
                                ) {
                                    userRegistering =
                                        binding.layFormOne.edtUserRegistering.text.toString()
                                    DateRegistering = binding.layFormOne.edtDate.text.toString()
                                    TimeRegistering = binding.layFormOne.edtTime.text.toString()

                                    ownerName = binding.layFormTwo.edtNameOwner.text.toString()
                                    ownerFamily = binding.layFormTwo.edtFamilyOwner.text.toString()

                                    // الگوی شماره‌های مجاز برای ایرانسل، همراه اول و رایتل
                                    val validPhoneNumberRegex =
                                        "^(09[1-3][0-9][0-9]\\d{6})\$".toRegex()

                                    binding.layFormTwo.edtMobilePhoneNumber.addTextChangedListener { it ->
                                        val input = it.toString()
                                        if (input.isNotEmpty() && !validPhoneNumberRegex.matches(
                                                input
                                            )
                                        ) {
                                            binding.layFormTwo.txtWarning3.text =
                                                "لطفاً شماره معتبر وارد کنید"
                                        }
                                    }

                                    ownerMobilePhone =
                                        binding.layFormTwo.edtMobilePhoneNumber.text.toString()

                                    priceMelk =
                                        binding.layFormThree.edtPriceMelk.text.toString() + " , " +
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

                    2 -> {
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
                                        "بعدی : ${resources.getString(R.string.txt_price_the_property)}"

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
                                        binding.circularProgressBar.progress = 37.5.toInt()

                                        binding.layFormThree.edtPriceMelk.requestFocus()
                                        showKeyboard()

                                        binding.txtTop.text = "3"
                                        binding.txtPageTitleTop.text =
                                            resources.getString(R.string.txt_price_the_property)
                                        binding.txtPageTitleBottom.text =
                                            "بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


                                        binding.img.background =
                                            resources.getDrawable(R.drawable.money_bag)
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
                                    binding.circularProgressBar.progress = 50

                                    binding.layFormFour.edtAddressFile.requestFocus()
                                    showKeyboard()

                                    binding.txtTop.text = "4"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_address_and_metrazh)
                                    binding.txtPageTitleBottom.text =
                                        "بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                    binding.img.background =
                                        resources.getDrawable(R.drawable.map_point)
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
                                    binding.circularProgressBar.progress = 62.5.toInt()


                                    binding.txtTop.text = "5"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                    binding.txtPageTitleBottom.text =
                                        "بعدی : ${resources.getString(R.string.txt_other_vizhegi)}"


                                    binding.img.background =
                                        resources.getDrawable(R.drawable.key_square)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_moshakhasat_kilidi)
                                }
                            }

                            5 -> {
                                counterStepsTwo = 5
                                if (binding.layFormFiveFive.edtTypeUser.text.toString() == "" &&
                                    binding.layFormFiveFive.edtTypeSanad.text.toString() == "" &&
                                    binding.layFormFiveFive.edtLocation.text.toString() == "" &&
                                    binding.layFormFiveFive.edtTabaghe.text.toString() == "" &&
                                    binding.layFormFiveFive.edtAgeBana.text.toString() == "" &&
                                    binding.layFormFiveFive.edtVaziyadMelk.text.toString() == "" &&
                                    binding.layFormFiveFive.edtCunterOtagh.text.toString() == "" &&
                                    binding.layFormFiveFive.edtNemaSakhteman.text.toString() == ""
                                ) {
                                    binding.layFormFiveFive.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring.animation = zAnim
                                    binding.layFormFiveFive.txtWarring1.text =
                                        resources.getString(R.string.txt_selection_type_user)

                                    binding.layFormFiveFive.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring2.animation = zAnim
                                    binding.layFormFiveFive.txtWarning2.text =
                                        resources.getString(R.string.txt_selection_type_sanad)

                                    binding.layFormFiveFive.layWarning3.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring3.animation = zAnim
                                    binding.layFormFiveFive.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_location)

                                    binding.layFormFiveFive.layWarning4.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring4.animation = zAnim
                                    binding.layFormFiveFive.txtWarning4.text =
                                        resources.getString(R.string.txt_selection_tabaghe)

                                    binding.layFormFiveFive.layWarning5.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring5.animation = zAnim
                                    binding.layFormFiveFive.txtWarning5.text =
                                        resources.getString(R.string.txt_selection_age_bana)

                                    binding.layFormFiveFive.layWarning6.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring6.animation = zAnim
                                    binding.layFormFiveFive.txtWarning6.text =
                                        resources.getString(R.string.txt_selection_vaziyad_melk)

                                    binding.layFormFiveFive.layWarning7.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring7.animation = zAnim
                                    binding.layFormFiveFive.txtWarning7.text =
                                        resources.getString(R.string.txt_selection_cunter_otagh)

                                    binding.layFormFiveFive.layWarning8.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring8.animation = zAnim
                                    binding.layFormFiveFive.txtWarning8.text =
                                        resources.getString(R.string.txt_selection_nema_sakhteman)

                                } else if (binding.layFormFiveFive.edtTypeUser.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning1.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring.animation = zAnim
                                    binding.layFormFiveFive.txtWarring1.text =
                                        resources.getString(R.string.txt_selection_type_user)
                                } else if (binding.layFormFiveFive.edtTypeSanad.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning2.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring2.animation = zAnim
                                    binding.layFormFiveFive.txtWarning2.text =
                                        resources.getString(R.string.txt_selection_type_sanad)
                                } else if (binding.layFormFiveFive.edtLocation.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning3.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring3.animation = zAnim
                                    binding.layFormFiveFive.txtWarning3.text =
                                        resources.getString(R.string.txt_enter_location)
                                } else if (binding.layFormFiveFive.edtTabaghe.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning4.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring4.animation = zAnim
                                    binding.layFormFiveFive.txtWarning4.text =
                                        resources.getString(R.string.txt_selection_tabaghe)
                                } else if (binding.layFormFiveFive.edtAgeBana.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning5.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring5.animation = zAnim
                                    binding.layFormFiveFive.txtWarning5.text =
                                        resources.getString(R.string.txt_selection_age_bana)
                                } else if (binding.layFormFiveFive.edtVaziyadMelk.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning6.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring6.animation = zAnim
                                    binding.layFormFiveFive.txtWarning6.text =
                                        resources.getString(R.string.txt_selection_vaziyad_melk)
                                } else if (binding.layFormFiveFive.edtCunterOtagh.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning7.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring7.animation = zAnim
                                    binding.layFormFiveFive.txtWarning7.text =
                                        resources.getString(R.string.txt_selection_cunter_otagh)
                                } else if (binding.layFormFiveFive.edtNemaSakhteman.text.toString() == "") {
                                    binding.layFormFiveFive.layWarning8.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring8.animation = zAnim
                                    binding.layFormFiveFive.txtWarning8.text =
                                        resources.getString(R.string.txt_selection_nema_sakhteman)
                                } else if (!binding.layFormFiveFive.radioBtn1Lay5.isChecked &&
                                    !binding.layFormFiveFive.radioBtn2Lay5.isChecked
                                ) {
                                    binding.layFormFiveFive.layWarning9.visibility = View.VISIBLE
                                    binding.layFormFiveFive.imgWarring9.animation = zAnim
                                } else {
                                    counterStepsTwo = 6
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.GONE
                                    binding.layScroll55.visibility = View.GONE
                                    binding.layScroll6.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 75


                                    binding.txtTop.text = "6"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_other_vizhegi)

                                    binding.layDes2.visibility = View.VISIBLE

                                    binding.txtPageTitleBottom.text =
                                        resources.getString(R.string.txt_facilities)


                                    binding.img.background =
                                        resources.getDrawable(R.drawable.widget)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_other_vizhegi)
                                }
                            }

                            6 -> {
                                counterStepsTwo = 6
                                if (binding.layFormSex.edtCounterAllTabaghat.text.toString() == "" &&
                                    binding.layFormSex.edtCounterAllVahedha.text.toString() == "" &&
                                    binding.layFormSex.edtCounterVahedhaDarTabaghe.text.toString() == "" &&
                                    binding.layFormSex.edtJahatSakhteman.text.toString() == "" &&
                                    binding.layFormSex.edtJahatVahed.text.toString() == "" &&
                                    binding.layFormSex.edtJensKaf.text.toString() == "" &&
                                    binding.layFormSex.edtCabinets.text.toString() == "" &&
                                    binding.layFormSex.edtWc.text.toString() == "" &&
                                    binding.layFormSex.edtGarmayesh.text.toString() == "" &&
                                    binding.layFormSex.edtSarmayesh.text.toString() == ""
                                ) {
                                    binding.layFormSex.layWarning1.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring.animation = zAnim
                                    binding.layFormSex.txtWarring1.text =
                                        resources.getString(R.string.txt_enter_counter_all_tabaghat)

                                    binding.layFormSex.layWarning2.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring2.animation = zAnim
                                    binding.layFormSex.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_counter_all_vahedha)

                                    binding.layFormSex.layWarning3.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring3.animation = zAnim
                                    binding.layFormSex.txtWarring3.text =
                                        resources.getString(R.string.txt_enter_counter_har_tabaghe)

                                    binding.layFormSex.layWarning4.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring4.animation = zAnim
                                    binding.layFormSex.txtWarring4.text =
                                        resources.getString(R.string.txt_selection_jahat_sakhteman)

                                    binding.layFormSex.layWarning5.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring5.animation = zAnim
                                    binding.layFormSex.txtWarring5.text =
                                        resources.getString(R.string.txt_selection_jahat_vahed)

                                    binding.layFormSex.layWarning6.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring6.animation = zAnim
                                    binding.layFormSex.txtWarring6.text =
                                        resources.getString(R.string.txt_selection_jense_kaf)

                                    binding.layFormSex.layWarning7.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring7.animation = zAnim
                                    binding.layFormSex.txtWarring7.text =
                                        resources.getString(R.string.txt_selection_kabinet)

                                    binding.layFormSex.layWarning8.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring8.animation = zAnim
                                    binding.layFormSex.txtWarring8.text =
                                        resources.getString(R.string.txt_selection_wc)

                                    binding.layFormSex.layWarning9.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring9.animation = zAnim
                                    binding.layFormSex.txtWarring9.text =
                                        resources.getString(R.string.txt_selection_garmayesh)

                                    binding.layFormSex.layWarning10.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring10.animation = zAnim
                                    binding.layFormSex.txtWarring10.text =
                                        resources.getString(R.string.txt_selection_sarmayesh)

                                } else if (binding.layFormSex.edtCounterAllTabaghat.text.toString() == "") {
                                    binding.layFormSex.layWarning1.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring.animation = zAnim
                                    binding.layFormSex.txtWarring1.text =
                                        resources.getString(R.string.txt_enter_counter_all_tabaghat)

                                } else if (binding.layFormSex.edtCounterAllVahedha.text.toString() == "") {
                                    binding.layFormSex.layWarning2.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring2.animation = zAnim
                                    binding.layFormSex.txtWarring2.text =
                                        resources.getString(R.string.txt_enter_counter_all_vahedha)

                                } else if (binding.layFormSex.edtCounterVahedhaDarTabaghe.text.toString() == "") {
                                    binding.layFormSex.layWarning3.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring3.animation = zAnim
                                    binding.layFormSex.txtWarring3.text =
                                        resources.getString(R.string.txt_enter_counter_har_tabaghe)

                                } else if (binding.layFormSex.edtJahatSakhteman.text.toString() == "") {
                                    binding.layFormSex.layWarning4.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring4.animation = zAnim
                                    binding.layFormSex.txtWarring4.text =
                                        resources.getString(R.string.txt_selection_jahat_sakhteman)

                                } else if (binding.layFormSex.edtJahatVahed.text.toString() == "") {
                                    binding.layFormSex.layWarning5.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring5.animation = zAnim
                                    binding.layFormSex.txtWarring5.text =
                                        resources.getString(R.string.txt_selection_jahat_vahed)

                                } else if (binding.layFormSex.edtJensKaf.text.toString() == "") {
                                    binding.layFormSex.layWarning6.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring6.animation = zAnim
                                    binding.layFormSex.txtWarring6.text =
                                        resources.getString(R.string.txt_selection_jense_kaf)

                                } else if (binding.layFormSex.edtCabinets.text.toString() == "") {
                                    binding.layFormSex.layWarning7.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring7.animation = zAnim
                                    binding.layFormSex.txtWarring7.text =
                                        resources.getString(R.string.txt_selection_kabinet)

                                } else if (binding.layFormSex.edtWc.text.toString() == "") {
                                    binding.layFormSex.layWarning8.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring8.animation = zAnim
                                    binding.layFormSex.txtWarring8.text =
                                        resources.getString(R.string.txt_selection_wc)

                                } else if (binding.layFormSex.edtGarmayesh.text.toString() == "") {
                                    binding.layFormSex.layWarning9.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring9.animation = zAnim
                                    binding.layFormSex.txtWarring9.text =
                                        resources.getString(R.string.txt_selection_garmayesh)

                                } else if (binding.layFormSex.edtSarmayesh.text.toString() == "") {
                                    binding.layFormSex.layWarning10.visibility = View.VISIBLE
                                    binding.layFormSex.imgWarring10.animation = zAnim
                                    binding.layFormSex.txtWarring10.text =
                                        resources.getString(R.string.txt_selection_sarmayesh)

                                } else {
                                    counterStepsTwo = 7
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.GONE
                                    binding.layScroll55.visibility = View.GONE
                                    binding.layScroll6.visibility = View.GONE
                                    binding.layScroll7.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 87.5.toInt()


                                    binding.txtTop.text = "7"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_facilities)

                                    binding.layDes2.visibility = View.VISIBLE

                                    binding.txtPageTitleBottom.text =
                                        resources.getString(R.string.txt_images)


                                    binding.img.background =
                                        resources.getDrawable(R.drawable.shield_check)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_facilities)
                                }
                            }

                            7 -> {
                                counterStepsTwo = 7
                                if (!binding.layFormSeven.checkAsansor.isChecked &&
                                    !binding.layFormSeven.checkParking.isChecked &&
                                    !binding.layFormSeven.checkMelkBazsaziShode.isChecked &&
                                    !binding.layFormSeven.checkJakozi.isChecked &&
                                    !binding.layFormSeven.checkAnbari.isChecked &&
                                    !binding.layFormSeven.checkEstakhr.isChecked &&
                                    !binding.layFormSeven.checkBalkon.isChecked &&
                                    !binding.layFormSeven.checkIphonTasviri.isChecked &&
                                    !binding.layFormSeven.checkSona.isChecked &&
                                    !binding.layFormSeven.checkLabi.isChecked &&
                                    !binding.layFormSeven.checkGazRomizi.isChecked &&
                                    !binding.layFormSeven.checkSalonVarzeshi.isChecked &&
                                    !binding.layFormSeven.checkHod.isChecked &&
                                    !binding.layFormSeven.checkSalonEjtemaat.isChecked &&
                                    !binding.layFormSeven.checkDarbZedSerghat.isChecked &&
                                    !binding.layFormSeven.checkAntenMarkazi.isChecked &&
                                    !binding.layFormSeven.checkKomodDivari.isChecked &&
                                    !binding.layFormSeven.checkJarobarghiMarkazi.isChecked &&
                                    !binding.layFormSeven.checkHayat.isChecked

                                ) {
                                    showDialogDoYouSure(requireActivity())
                                } else {
                                    counterStepsTwo = 8
                                    binding.layScroll.visibility = View.GONE
                                    binding.layScroll2.visibility = View.GONE
                                    binding.layScroll3.visibility = View.GONE
                                    binding.layScroll4.visibility = View.GONE
                                    binding.layScroll55.visibility = View.GONE
                                    binding.layScroll6.visibility = View.GONE
                                    binding.layScroll7.visibility = View.GONE
                                    binding.layScroll8.visibility = View.VISIBLE
                                    binding.circularProgressBar.progress = 100


                                    binding.txtTop.text = "8"
                                    binding.txtPageTitleTop.text =
                                        resources.getString(R.string.txt_images)

                                    binding.layDes2.visibility = View.GONE

                                    binding.img.background =
                                        resources.getDrawable(R.drawable.camera)
                                    binding.txtPageTitle.text =
                                        resources.getString(R.string.txt_images)

                                    binding.btnNext123.text =
                                        resources.getString(R.string.txt_register2)
                                }
                            }

                            8 ->{
                                userRegistering =
                                    binding.layFormOne.edtUserRegistering.text.toString()
                                DateRegistering = binding.layFormOne.edtDate.text.toString()
                                TimeRegistering = binding.layFormOne.edtTime.text.toString()

                                ownerName = binding.layFormTwo.edtNameOwner.text.toString()
                                ownerFamily = binding.layFormTwo.edtFamilyOwner.text.toString()
                                val validPhoneNumberRegex =
                                    "^(09[1-3][0-9][0-9]\\d{6})\$".toRegex()
                                binding.layFormTwo.edtMobilePhoneNumber.addTextChangedListener { it ->
                                    val input = it.toString()
                                    if (input.isNotEmpty() && !validPhoneNumberRegex.matches(
                                            input
                                        )
                                    ) {
                                        binding.layFormTwo.txtWarning3.text =
                                            "لطفاً شماره معتبر وارد کنید"
                                    }
                                }
                                ownerMobilePhone =
                                    binding.layFormTwo.edtMobilePhoneNumber.text.toString()

                                priceMelk =
                                    binding.layFormThree.edtPriceMelk.text.toString() + " , " +
                                            binding.layFormThree.txtPrice.text.toString()

                                addressFile = binding.layFormFour.edtAddressFile.text.toString()
                                metrazhMoraba =
                                    binding.layFormFour.edtMetrazhMoraba.text.toString() + "متر"

                                typeUser = binding.layFormFiveFive.edtTypeUser.text.toString()
                                typeSanad = binding.layFormFiveFive.edtTypeSanad.text.toString()
                                location = binding.layFormFiveFive.edtLocation.text.toString()
                                tabaghe = binding.layFormFiveFive.edtTabaghe.text.toString()
                                ageBana = binding.layFormFiveFive.edtAgeBana.text.toString()
                                vaziyatMelk =
                                    binding.layFormFiveFive.edtVaziyadMelk.text.toString()
                                cunterOtagh =
                                    binding.layFormFiveFive.edtCunterOtagh.text.toString()
                                nemaSakhteman =
                                    binding.layFormFiveFive.edtNemaSakhteman.text.toString()
                                if (binding.layFormFiveFive.radioBtn1Lay5.isChecked) {
                                    sureVame =
                                        binding.layFormFiveFive.radioBtn1Lay5.tag.toString()
                                } else if (binding.layFormFiveFive.radioBtn2Lay5.isChecked) {
                                    sureVame =
                                        binding.layFormFiveFive.radioBtn2Lay5.tag.toString()
                                }
                                description =
                                    binding.layFormFiveFive.edtDescription.text.toString()
                                counterAllTabaghat =
                                    binding.layFormSex.edtCounterAllTabaghat.text.toString()
                                counterAllVahedha =
                                    binding.layFormSex.edtCounterAllVahedha.text.toString()
                                counterVahedhaDarTabaghe =
                                    binding.layFormSex.edtCounterVahedhaDarTabaghe.text.toString()
                                jahatSakhteman =
                                    binding.layFormSex.edtJahatSakhteman.text.toString()
                                jahatVahed = binding.layFormSex.edtJahatVahed.text.toString()
                                jensKaf = binding.layFormSex.edtJensKaf.text.toString()
                                cabinets = binding.layFormSex.edtCabinets.text.toString()
                                wC = binding.layFormSex.edtWc.text.toString()
                                garmayesh = binding.layFormSex.edtGarmayesh.text.toString()
                                sarmayesh = binding.layFormSex.edtSarmayesh.text.toString()

                                checkAsansor =
                                    if (binding.layFormSeven.checkAsansor.isChecked) {
                                        binding.layFormSeven.checkAsansor.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkParking =
                                    if (binding.layFormSeven.checkParking.isChecked) {
                                        binding.layFormSeven.checkParking.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkMelkBazsaziShode =
                                    if (binding.layFormSeven.checkMelkBazsaziShode.isChecked) {
                                        binding.layFormSeven.checkMelkBazsaziShode.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkJakozi =
                                    if (binding.layFormSeven.checkJakozi.isChecked) {
                                        binding.layFormSeven.checkJakozi.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkAnbari =
                                    if (binding.layFormSeven.checkAnbari.isChecked) {
                                        binding.layFormSeven.checkAnbari.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkEstakhr =
                                    if (binding.layFormSeven.checkEstakhr.isChecked) {
                                        binding.layFormSeven.checkEstakhr.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkBalkon =
                                    if (binding.layFormSeven.checkBalkon.isChecked) {
                                        binding.layFormSeven.checkBalkon.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkIphonTasviri =
                                    if (binding.layFormSeven.checkIphonTasviri.isChecked) {
                                        binding.layFormSeven.checkIphonTasviri.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkSona =
                                    if (binding.layFormSeven.checkSona.isChecked) {
                                        binding.layFormSeven.checkSona.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkLabi =
                                    if (binding.layFormSeven.checkLabi.isChecked) {
                                        binding.layFormSeven.checkLabi.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkGazRomizi =
                                    if (binding.layFormSeven.checkGazRomizi.isChecked) {
                                        binding.layFormSeven.checkGazRomizi.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkSalonVarzeshi =
                                    if (binding.layFormSeven.checkSalonVarzeshi.isChecked) {
                                        binding.layFormSeven.checkSalonVarzeshi.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkHod =
                                    if (binding.layFormSeven.checkHod.isChecked) {
                                        binding.layFormSeven.checkHod.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkSalonEjtemaat =
                                    if (binding.layFormSeven.checkSalonEjtemaat.isChecked) {
                                        binding.layFormSeven.checkSalonEjtemaat.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkDarbZedSerghat =
                                    if (binding.layFormSeven.checkDarbZedSerghat.isChecked) {
                                        binding.layFormSeven.checkDarbZedSerghat.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkAntenMarkazi =
                                    if (binding.layFormSeven.checkAntenMarkazi.isChecked) {
                                        binding.layFormSeven.checkAntenMarkazi.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkKomodDivari =
                                    if (binding.layFormSeven.checkKomodDivari.isChecked) {
                                        binding.layFormSeven.checkKomodDivari.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkJarobarghiMarkazi =
                                    if (binding.layFormSeven.checkJarobarghiMarkazi.isChecked) {
                                        binding.layFormSeven.checkJarobarghiMarkazi.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                checkHayat =
                                    if (binding.layFormSeven.checkHayat.isChecked) {
                                        binding.layFormSeven.checkHayat.tag.toString()
                                    } else {
                                        getString(R.string.txt_does_not_have)
                                    }

                                if (binding.layFormSeven.checkSelectAll.isChecked) {
                                    checkAsansor = getString(R.string.txt_has_it)
                                    checkMelkBazsaziShode = getString(R.string.txt_has_it)
                                    checkAnbari = getString(R.string.txt_has_it)
                                    checkBalkon = getString(R.string.txt_has_it)
                                    checkSona = getString(R.string.txt_has_it)
                                    checkGazRomizi = getString(R.string.txt_has_it)
                                    checkHod = getString(R.string.txt_has_it)
                                    checkDarbZedSerghat = getString(R.string.txt_has_it)
                                    checkKomodDivari = getString(R.string.txt_has_it)
                                    checkHayat = getString(R.string.txt_has_it)
                                    checkParking = getString(R.string.txt_has_it)
                                    checkJakozi = getString(R.string.txt_has_it)
                                    checkEstakhr = getString(R.string.txt_has_it)
                                    checkIphonTasviri = getString(R.string.txt_has_it)
                                    checkLabi = getString(R.string.txt_has_it)
                                    checkSalonVarzeshi = getString(R.string.txt_has_it)
                                    checkSalonEjtemaat = getString(R.string.txt_has_it)
                                    checkAntenMarkazi = getString(R.string.txt_has_it)
                                    checkJarobarghiMarkazi = getString(R.string.txt_has_it)
                                    checkSelectAll = getString(R.string.txt_select_all)
                                } else {
                                    getString(R.string.txt_not_selected)
                                }

                                val listImg = ArrayList<String>() // todo get code ahmad



                                val registerBuyAndSellFormTwo = RegisterBuyAndSellModelFormTwo(
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
                                    typeUser,
                                    typeSanad,
                                    location,
                                    tabaghe,
                                    ageBana,
                                    vaziyatMelk,
                                    cunterOtagh,
                                    nemaSakhteman,
                                    sureVame,
                                    description,
                                    counterAllTabaghat,
                                    counterAllVahedha,
                                    counterVahedhaDarTabaghe,
                                    jahatSakhteman,
                                    jahatVahed,
                                    jensKaf,
                                    cabinets,
                                    wC,
                                    garmayesh,
                                    sarmayesh,
                                    checkAsansor,
                                    checkParking,
                                    checkMelkBazsaziShode,
                                    checkJakozi,
                                    checkAnbari,
                                    checkEstakhr,
                                    checkBalkon,
                                    checkIphonTasviri,
                                    checkSona,
                                    checkLabi,
                                    checkGazRomizi,
                                    checkSalonVarzeshi,
                                    checkHod,
                                    checkSalonEjtemaat,
                                    checkDarbZedSerghat,
                                    checkAntenMarkazi,
                                    checkKomodDivari,
                                    checkJarobarghiMarkazi,
                                    checkHayat,
                                    checkSelectAll
//                                        listImg
                                )

                                val result = db.dBDao()
                                    .upsertRegisterBuyAndSellFormTow(registerBuyAndSellFormTwo)
                                if (result > 0) {
                                    showDialogDoYouContinue(requireActivity())
                                } else {
                                    Toast.makeText(
                                        requireActivity(),
                                        getString(R.string.txt_save_infonmation_failure),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                }


            }

            R.id.btn_previous -> {
                when (counter) {
                    1 -> {
                        when (counterStepsOne) {
                            2 -> {
                                counterStepsOne = 1
                                binding.layScroll.visibility = View.VISIBLE
                                binding.layScroll2.visibility = View.GONE
                                binding.circularProgressBar.progress = 20

                                binding.txtTop.text = "1"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_info_paye_registering)
                                binding.txtPageTitleBottom.text =
                                    "بعدی : ${resources.getString(R.string.txt_information_owner)}"


                                binding.img.background =
                                    resources.getDrawable(R.drawable.document_text)
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
                                    "بعدی : ${resources.getString(R.string.txt_price_the_property)}"


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
                                    "بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


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
                                    "بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                binding.img.background = resources.getDrawable(R.drawable.map_point)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_price_the_property)
                            }
                        }
                    }

                    2 -> {
                        when (counterStepsTwo) {
                            2 -> {
                                counterStepsTwo = 1
                                binding.layScroll.visibility = View.VISIBLE
                                binding.layScroll2.visibility = View.GONE
                                binding.circularProgressBar.progress = 12.5.toInt()

                                binding.txtTop.text = "1"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_info_paye_registering)
                                binding.txtPageTitleBottom.text =
                                    "بعدی : ${resources.getString(R.string.txt_information_owner)}"


                                binding.img.background =
                                    resources.getDrawable(R.drawable.document_text)
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
                                    "بعدی : ${resources.getString(R.string.txt_price_the_property)}"


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
                                    "بعدی : ${resources.getString(R.string.txt_address_and_metrazh)}"


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
                                    "بعدی : ${resources.getString(R.string.txt_moshakhasat_kilidi)}"


                                binding.img.background = resources.getDrawable(R.drawable.map_point)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_address_and_metrazh)
                            }

                            6 -> {
                                counterStepsTwo = 5
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.GONE
                                binding.layScroll4.visibility = View.GONE
                                binding.layScroll55.visibility = View.VISIBLE
                                binding.layScroll6.visibility = View.GONE
                                binding.circularProgressBar.progress = 62.5.toInt()

                                binding.txtTop.text = "5"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_moshakhasat_kilidi)
                                binding.layDes2.visibility = View.VISIBLE
                                binding.txtPageTitleBottom.text =
                                    "بعدی : ${resources.getString(R.string.txt_other_vizhegi)}"


                                binding.img.background =
                                    resources.getDrawable(R.drawable.key_square)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_moshakhasat_kilidi)
                            }

                            7 -> {
                                counterStepsTwo = 6
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.GONE
                                binding.layScroll4.visibility = View.GONE
                                binding.layScroll55.visibility = View.GONE
                                binding.layScroll6.visibility = View.VISIBLE
                                binding.layScroll7.visibility = View.GONE
                                binding.circularProgressBar.progress = 75

                                binding.txtTop.text = "6"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_other_vizhegi)
                                binding.layDes2.visibility = View.VISIBLE
                                binding.txtPageTitleBottom.text =
                                    "بعدی : ${resources.getString(R.string.txt_facilities)}"


                                binding.img.background = resources.getDrawable(R.drawable.widget)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_other_vizhegi)
                            }

                            8 -> {
                                counterStepsTwo = 7
                                binding.layScroll.visibility = View.GONE
                                binding.layScroll2.visibility = View.GONE
                                binding.layScroll3.visibility = View.GONE
                                binding.layScroll4.visibility = View.GONE
                                binding.layScroll55.visibility = View.GONE
                                binding.layScroll6.visibility = View.GONE
                                binding.layScroll7.visibility = View.VISIBLE
                                binding.layScroll8.visibility = View.GONE
                                binding.circularProgressBar.progress = 87.5.toInt()

                                binding.txtTop.text = "7"
                                binding.txtPageTitleTop.text =
                                    resources.getString(R.string.txt_facilities)
                                binding.layDes2.visibility = View.VISIBLE
                                binding.txtPageTitleBottom.text =
                                    "بعدی : ${resources.getString(R.string.txt_images)}"


                                binding.img.background =
                                    resources.getDrawable(R.drawable.shield_check)
                                binding.txtPageTitle.text =
                                    resources.getString(R.string.txt_facilities)
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

            R.id.radio_btn1_lay_5 -> {
                binding.layFormFiveFive.radioBtn1Lay5.isChecked = true
                binding.layFormFiveFive.radioBtn2Lay5.isChecked = false
                binding.layFormFiveFive.layWarning9.visibility = View.GONE
                binding.layFormFiveFive.layRadioBtn1.background =
                    resources.getDrawable(R.drawable.border9)
                binding.layFormFiveFive.layRadioBtn2.background =
                    resources.getDrawable(R.drawable.border4)
            }

            R.id.radio_btn2_lay_5 -> {
                binding.layFormFiveFive.radioBtn1Lay5.isChecked = false
                binding.layFormFiveFive.radioBtn2Lay5.isChecked = true
                binding.layFormFiveFive.layWarning9.visibility = View.GONE
                binding.layFormFiveFive.layRadioBtn1.background =
                    resources.getDrawable(R.drawable.border4)
                binding.layFormFiveFive.layRadioBtn2.background =
                    resources.getDrawable(R.drawable.border9)
            }

            R.id.edt_type_user -> {
                countGetList = 1
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false
            }

            R.id.edt_type_sanad -> {
                countGetList = 2
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_age_bana -> {
                countGetList = 3
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_tabaghe -> {
                countGetList = 4
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_cunter_otagh -> {
                countGetList = 5
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_vaziyad_melk -> {
                countGetList = 6
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_wc -> {
                countGetList = 7
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_nema_sakhteman -> {
                countGetList = 8
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_cabinets -> {
                countGetList = 9
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_jahat_sakhteman -> {
                countGetList = 10
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_jahat_vahed -> {
                countGetList = 11
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_jens_kaf -> {
                countGetList = 12
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_garmayesh -> {
                countGetList = 13
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.edt_sarmayesh -> {
                countGetList = 14
                Hawks.save("countGetList", countGetList as Int)

                btmSheetKharidForosh.show(childFragmentManager, "")
                btmSheetKharidForosh.isCancelable = false

            }

            R.id.check_asansor -> {
                if (binding.layFormSeven.checkAsansor.isChecked) {
                    binding.layFormSeven.checkAsansor.isChecked = true
                    binding.layFormSeven.layAsansor.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkAsansor.isChecked = false
                    binding.layFormSeven.layAsansor.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_parking -> {
                if (binding.layFormSeven.checkParking.isChecked) {
                    binding.layFormSeven.checkParking.isChecked = true
                    binding.layFormSeven.layParking.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkParking.isChecked = false
                    binding.layFormSeven.layParking.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_melk_bazsazi_shode -> {
                if (binding.layFormSeven.checkMelkBazsaziShode.isChecked) {
                    binding.layFormSeven.checkMelkBazsaziShode.isChecked = true
                    binding.layFormSeven.layMelkBazsaziShode.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkMelkBazsaziShode.isChecked = false
                    binding.layFormSeven.layMelkBazsaziShode.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_jakozi -> {
                if (binding.layFormSeven.checkJakozi.isChecked) {
                    binding.layFormSeven.checkJakozi.isChecked = true
                    binding.layFormSeven.layJakozi.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkJakozi.isChecked = false
                    binding.layFormSeven.layJakozi.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_anbari -> {
                if (binding.layFormSeven.checkAnbari.isChecked) {
                    binding.layFormSeven.checkAnbari.isChecked = true
                    binding.layFormSeven.layAnbari.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkAnbari.isChecked = false
                    binding.layFormSeven.layAnbari.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_estakhr -> {
                if (binding.layFormSeven.checkEstakhr.isChecked) {
                    binding.layFormSeven.checkEstakhr.isChecked = true
                    binding.layFormSeven.layEstakhr.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkEstakhr.isChecked = false
                    binding.layFormSeven.layEstakhr.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_balkon -> {
                if (binding.layFormSeven.checkBalkon.isChecked) {
                    binding.layFormSeven.checkBalkon.isChecked = true
                    binding.layFormSeven.layBalkon.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkBalkon.isChecked = false
                    binding.layFormSeven.layBalkon.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_iphon_tasviri -> {
                if (binding.layFormSeven.checkIphonTasviri.isChecked) {
                    binding.layFormSeven.checkIphonTasviri.isChecked = true
                    binding.layFormSeven.layIphonTasviri.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkIphonTasviri.isChecked = false
                    binding.layFormSeven.layIphonTasviri.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_sona -> {
                if (binding.layFormSeven.checkSona.isChecked) {
                    binding.layFormSeven.checkSona.isChecked = true
                    binding.layFormSeven.laySona.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkSona.isChecked = false
                    binding.layFormSeven.laySona.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_labi -> {
                if (binding.layFormSeven.checkLabi.isChecked) {
                    binding.layFormSeven.checkLabi.isChecked = true
                    binding.layFormSeven.layLabi.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkLabi.isChecked = false
                    binding.layFormSeven.layLabi.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_gaz_romizi -> {
                if (binding.layFormSeven.checkGazRomizi.isChecked) {
                    binding.layFormSeven.checkGazRomizi.isChecked = true
                    binding.layFormSeven.layGazRomizi.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkGazRomizi.isChecked = false
                    binding.layFormSeven.layGazRomizi.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_salon_varzeshi -> {
                if (binding.layFormSeven.checkSalonVarzeshi.isChecked) {
                    binding.layFormSeven.checkSalonVarzeshi.isChecked = true
                    binding.layFormSeven.laySalonVarzeshi.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkSalonVarzeshi.isChecked = false
                    binding.layFormSeven.laySalonVarzeshi.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_hod -> {
                if (binding.layFormSeven.checkHod.isChecked) {
                    binding.layFormSeven.checkHod.isChecked = true
                    binding.layFormSeven.layHod.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkHod.isChecked = false
                    binding.layFormSeven.layHod.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_salon_ejtemaat -> {
                if (binding.layFormSeven.checkSalonEjtemaat.isChecked) {
                    binding.layFormSeven.checkSalonEjtemaat.isChecked = true
                    binding.layFormSeven.laySalonEjtemaat.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkSalonEjtemaat.isChecked = false
                    binding.layFormSeven.laySalonEjtemaat.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_darb_zed_serghat -> {
                if (binding.layFormSeven.checkDarbZedSerghat.isChecked) {
                    binding.layFormSeven.checkDarbZedSerghat.isChecked = true
                    binding.layFormSeven.layDarbZedSerghat.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkDarbZedSerghat.isChecked = false
                    binding.layFormSeven.layDarbZedSerghat.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_anten_markazi -> {
                if (binding.layFormSeven.checkAntenMarkazi.isChecked) {
                    binding.layFormSeven.checkAntenMarkazi.isChecked = true
                    binding.layFormSeven.layAntenMarkazi.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkAntenMarkazi.isChecked = false
                    binding.layFormSeven.layAntenMarkazi.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_komod_divari -> {
                if (binding.layFormSeven.checkKomodDivari.isChecked) {
                    binding.layFormSeven.checkKomodDivari.isChecked = true
                    binding.layFormSeven.layKomodDivari.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkKomodDivari.isChecked = false
                    binding.layFormSeven.layKomodDivari.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_jarobarghi_markazi -> {
                if (binding.layFormSeven.checkJarobarghiMarkazi.isChecked) {
                    binding.layFormSeven.checkJarobarghiMarkazi.isChecked = true
                    binding.layFormSeven.layJarobarghiMarkazi.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkJarobarghiMarkazi.isChecked = false
                    binding.layFormSeven.layJarobarghiMarkazi.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_hayat -> {
                if (binding.layFormSeven.checkHayat.isChecked) {
                    binding.layFormSeven.checkHayat.isChecked = true
                    binding.layFormSeven.layHayat.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.checkHayat.isChecked = false
                    binding.layFormSeven.layHayat.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.check_select_all -> {
                if (binding.layFormSeven.checkSelectAll.isChecked) {
                    binding.layFormSeven.laySelectAll.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkAnbari.isChecked = true
                    binding.layFormSeven.layAnbari.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkAsansor.isChecked = true
                    binding.layFormSeven.layAsansor.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkBalkon.isChecked = true
                    binding.layFormSeven.layBalkon.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkParking.isChecked = true
                    binding.layFormSeven.layParking.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkIphonTasviri.isChecked = true
                    binding.layFormSeven.layIphonTasviri.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkEstakhr.isChecked = true
                    binding.layFormSeven.layEstakhr.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkSona.isChecked = true
                    binding.layFormSeven.laySona.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkGazRomizi.isChecked = true
                    binding.layFormSeven.layGazRomizi.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkHod.isChecked = true
                    binding.layFormSeven.layHod.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkDarbZedSerghat.isChecked = true
                    binding.layFormSeven.layDarbZedSerghat.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkHayat.isChecked = true
                    binding.layFormSeven.layHayat.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkJakozi.isChecked = true
                    binding.layFormSeven.layJakozi.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkLabi.isChecked = true
                    binding.layFormSeven.layLabi.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkSalonVarzeshi.isChecked = true
                    binding.layFormSeven.laySalonVarzeshi.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkSalonEjtemaat.isChecked = true
                    binding.layFormSeven.laySalonEjtemaat.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkAntenMarkazi.isChecked = true
                    binding.layFormSeven.layAntenMarkazi.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkJarobarghiMarkazi.isChecked = true
                    binding.layFormSeven.layJarobarghiMarkazi.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkKomodDivari.isChecked = true
                    binding.layFormSeven.layKomodDivari.background =
                        resources.getDrawable(R.drawable.border9)

                    binding.layFormSeven.checkMelkBazsaziShode.isChecked = true
                    binding.layFormSeven.layMelkBazsaziShode.background =
                        resources.getDrawable(R.drawable.border9)

                } else {
                    binding.layFormSeven.laySelectAll.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkAnbari.isChecked = false
                    binding.layFormSeven.layAnbari.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkAsansor.isChecked = false
                    binding.layFormSeven.layAsansor.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkBalkon.isChecked = false
                    binding.layFormSeven.layBalkon.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkParking.isChecked = false
                    binding.layFormSeven.layParking.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkIphonTasviri.isChecked = false
                    binding.layFormSeven.layIphonTasviri.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkEstakhr.isChecked = false
                    binding.layFormSeven.layEstakhr.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkSona.isChecked = false
                    binding.layFormSeven.laySona.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkGazRomizi.isChecked = false
                    binding.layFormSeven.layGazRomizi.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkHod.isChecked = false
                    binding.layFormSeven.layHod.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkDarbZedSerghat.isChecked = false
                    binding.layFormSeven.layDarbZedSerghat.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkHayat.isChecked = false
                    binding.layFormSeven.layHayat.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkJakozi.isChecked = false
                    binding.layFormSeven.layJakozi.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkLabi.isChecked = false
                    binding.layFormSeven.layLabi.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkSalonVarzeshi.isChecked = false
                    binding.layFormSeven.laySalonVarzeshi.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkSalonEjtemaat.isChecked = false
                    binding.layFormSeven.laySalonEjtemaat.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkAntenMarkazi.isChecked = false
                    binding.layFormSeven.layAntenMarkazi.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkJarobarghiMarkazi.isChecked = false
                    binding.layFormSeven.layJarobarghiMarkazi.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkKomodDivari.isChecked = false
                    binding.layFormSeven.layKomodDivari.background =
                        resources.getDrawable(R.drawable.border4)

                    binding.layFormSeven.checkMelkBazsaziShode.isChecked = false
                    binding.layFormSeven.layMelkBazsaziShode.background =
                        resources.getDrawable(R.drawable.border4)
                }
            }

            R.id.lay_upload_image,
            R.id.img_upload_image,
            R.id.txt_upload_image -> {
                showDialogInactive(requireActivity())
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
            if (binding.layFormFive.edtDescription.text.toString() != "" &&
                binding.layFormFive.edtDescription.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormFive.edtDescription.text.toString())
                        .find()
                ) {
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


        // Todo Text Watcher Step Five_Five
        binding.layFormFiveFive.edtTypeUser.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtTypeUser.text.toString()).find()) {
                binding.layFormFiveFive.layWarning1.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring.animation = zAnim
                binding.layFormFiveFive.txtWarring1.text =
                    resources.getString(R.string.txt_selection_type_user)
                binding.layFormFiveFive.edtTypeUser.setText("")

            } else {
                binding.layFormFiveFive.layWarning1.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtTypeSanad.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtTypeSanad.text.toString()).find()) {
                binding.layFormFiveFive.layWarning2.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring2.animation = zAnim
                binding.layFormFiveFive.txtWarning2.text =
                    resources.getString(R.string.txt_selection_type_sanad)
                binding.layFormFiveFive.edtTypeSanad.setText("")

            } else {
                binding.layFormFiveFive.layWarning2.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtLocation.addTextChangedListener {
            if (binding.layFormFiveFive.edtLocation.text.toString() != "" &&
                binding.layFormFiveFive.edtLocation.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormFiveFive.edtLocation.text.toString())
                        .find()
                ) {
                    binding.layFormFiveFive.layWarning3.visibility = View.VISIBLE
                    binding.layFormFiveFive.imgWarring3.animation = zAnim
                    binding.layFormFiveFive.txtWarning3.text =
                        resources.getString(R.string.txt_please_enter_lango_farsi)
                    binding.layFormFiveFive.edtLocation.setText("")

                } else {
                    binding.layFormFiveFive.layWarning3.visibility = View.GONE
                }
            }
        }
        binding.layFormFiveFive.edtTabaghe.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtTabaghe.text.toString()).find()) {
                binding.layFormFiveFive.layWarning4.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring4.animation = zAnim
                binding.layFormFiveFive.txtWarning4.text =
                    resources.getString(R.string.txt_selection_tabaghe)
                binding.layFormFiveFive.edtTabaghe.setText("")

            } else {
                binding.layFormFiveFive.layWarning4.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtAgeBana.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtAgeBana.text.toString()).find()) {
                binding.layFormFiveFive.layWarning5.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring5.animation = zAnim
                binding.layFormFiveFive.txtWarning5.text =
                    resources.getString(R.string.txt_selection_age_bana)
                binding.layFormFiveFive.edtAgeBana.setText("")

            } else {
                binding.layFormFiveFive.layWarning5.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtVaziyadMelk.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtVaziyadMelk.text.toString())
                    .find()
            ) {
                binding.layFormFiveFive.layWarning6.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring6.animation = zAnim
                binding.layFormFiveFive.txtWarning6.text =
                    resources.getString(R.string.txt_selection_vaziyad_melk)
                binding.layFormFiveFive.edtVaziyadMelk.setText("")

            } else {
                binding.layFormFiveFive.layWarning6.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtCunterOtagh.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtCunterOtagh.text.toString())
                    .find()
            ) {
                binding.layFormFiveFive.layWarning7.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring7.animation = zAnim
                binding.layFormFiveFive.txtWarning7.text =
                    resources.getString(R.string.txt_selection_cunter_otagh)
                binding.layFormFiveFive.edtCunterOtagh.setText("")

            } else {
                binding.layFormFiveFive.layWarning7.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtNemaSakhteman.addTextChangedListener {
            if (pattern.matcher(binding.layFormFiveFive.edtNemaSakhteman.text.toString())
                    .find()
            ) {
                binding.layFormFiveFive.layWarning8.visibility = View.VISIBLE
                binding.layFormFiveFive.imgWarring8.animation = zAnim
                binding.layFormFiveFive.txtWarning8.text =
                    resources.getString(R.string.txt_selection_nema_sakhteman)
                binding.layFormFiveFive.edtNemaSakhteman.setText("")

            } else {
                binding.layFormFiveFive.layWarning8.visibility = View.GONE
            }
        }
        binding.layFormFiveFive.edtDescription.addTextChangedListener {
            if (binding.layFormFiveFive.edtDescription.text.toString() != "" &&
                binding.layFormFiveFive.edtDescription.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormFiveFive.edtDescription.text.toString())
                        .find()
                ) {
                    binding.layFormFiveFive.layWarning10.visibility = View.VISIBLE
                    binding.layFormFiveFive.imgWarring10.animation = zAnim
                    binding.layFormFiveFive.txtWarning10.text =
                        resources.getString(R.string.txt_please_enter_lango_farsi)
                    binding.layFormFiveFive.edtDescription.setText("")

                } else {
                    binding.layFormFiveFive.layWarning10.visibility = View.GONE
                }
            }

        }


        // Todo Text Watcher Step Sex
        binding.layFormSex.edtCounterAllTabaghat.addTextChangedListener {
            if (binding.layFormSex.edtCounterAllTabaghat.text.toString() != "" &&
                binding.layFormSex.edtCounterAllTabaghat.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormSex.edtCounterAllTabaghat.text.toString())
                        .find()
                ) {
                    binding.layFormSex.layWarning1.visibility = View.VISIBLE
                    binding.layFormSex.imgWarring.animation = zAnim
                    binding.layFormSex.txtWarring1.text =
                        resources.getString(R.string.txt_enter_counter_all_tabaghat)
                    binding.layFormSex.edtCounterAllTabaghat.setText("")

                } else {
                    binding.layFormSex.layWarning1.visibility = View.GONE
                }
            }
        }
        binding.layFormSex.edtCounterAllVahedha.addTextChangedListener {
            if (binding.layFormSex.edtCounterAllVahedha.text.toString() != "" &&
                binding.layFormSex.edtCounterAllVahedha.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormSex.edtCounterAllVahedha.text.toString())
                        .find()
                ) {
                    binding.layFormSex.layWarning2.visibility = View.VISIBLE
                    binding.layFormSex.imgWarring2.animation = zAnim
                    binding.layFormSex.txtWarring2.text =
                        resources.getString(R.string.txt_enter_counter_all_vahedha)
                    binding.layFormSex.edtCounterAllVahedha.setText("")

                } else {
                    binding.layFormSex.layWarning2.visibility = View.GONE
                }
            }
        }
        binding.layFormSex.edtCounterVahedhaDarTabaghe.addTextChangedListener {
            if (binding.layFormSex.edtCounterVahedhaDarTabaghe.text.toString() != "" &&
                binding.layFormSex.edtCounterVahedhaDarTabaghe.text.toString() != " "
            ) {
                if (pattern.matcher(binding.layFormSex.edtCounterVahedhaDarTabaghe.text.toString())
                        .find()
                ) {
                    binding.layFormSex.layWarning3.visibility = View.VISIBLE
                    binding.layFormSex.imgWarring3.animation = zAnim
                    binding.layFormSex.txtWarring3.text =
                        resources.getString(R.string.txt_enter_counter_har_tabaghe)
                    binding.layFormSex.edtCounterVahedhaDarTabaghe.setText("")

                } else {
                    binding.layFormSex.layWarning3.visibility = View.GONE
                }
            }
        }
        binding.layFormSex.edtJahatSakhteman.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtJahatSakhteman.text.toString()).find()) {
                binding.layFormSex.layWarning4.visibility = View.VISIBLE
                binding.layFormSex.imgWarring4.animation = zAnim
                binding.layFormSex.txtWarring4.text =
                    resources.getString(R.string.txt_selection_jahat_sakhteman)
                binding.layFormSex.edtJahatSakhteman.setText("")

            } else {
                binding.layFormSex.layWarning4.visibility = View.GONE
            }
        }
        binding.layFormSex.edtJahatVahed.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtJahatVahed.text.toString()).find()) {
                binding.layFormSex.layWarning5.visibility = View.VISIBLE
                binding.layFormSex.imgWarring5.animation = zAnim
                binding.layFormSex.txtWarring5.text =
                    resources.getString(R.string.txt_selection_jahat_vahed)
                binding.layFormSex.edtJahatVahed.setText("")

            } else {
                binding.layFormSex.layWarning5.visibility = View.GONE
            }
        }
        binding.layFormSex.edtJensKaf.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtJensKaf.text.toString()).find()) {
                binding.layFormSex.layWarning6.visibility = View.VISIBLE
                binding.layFormSex.imgWarring6.animation = zAnim
                binding.layFormSex.txtWarring6.text =
                    resources.getString(R.string.txt_selection_jense_kaf)
                binding.layFormSex.edtJensKaf.setText("")

            } else {
                binding.layFormSex.layWarning6.visibility = View.GONE
            }
        }
        binding.layFormSex.edtCabinets.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtCabinets.text.toString()).find()) {
                binding.layFormSex.layWarning7.visibility = View.VISIBLE
                binding.layFormSex.imgWarring7.animation = zAnim
                binding.layFormSex.txtWarring7.text =
                    resources.getString(R.string.txt_selection_kabinet)
                binding.layFormSex.edtCabinets.setText("")

            } else {
                binding.layFormSex.layWarning7.visibility = View.GONE
            }
        }
        binding.layFormSex.edtWc.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtWc.text.toString()).find()) {
                binding.layFormSex.layWarning8.visibility = View.VISIBLE
                binding.layFormSex.imgWarring8.animation = zAnim
                binding.layFormSex.txtWarring8.text =
                    resources.getString(R.string.txt_selection_wc)
                binding.layFormSex.edtWc.setText("")

            } else {
                binding.layFormSex.layWarning8.visibility = View.GONE
            }
        }
        binding.layFormSex.edtGarmayesh.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtGarmayesh.text.toString()).find()) {
                binding.layFormSex.layWarning9.visibility = View.VISIBLE
                binding.layFormSex.imgWarring9.animation = zAnim
                binding.layFormSex.txtWarring9.text =
                    resources.getString(R.string.txt_selection_garmayesh)
                binding.layFormSex.edtGarmayesh.setText("")

            } else {
                binding.layFormSex.layWarning9.visibility = View.GONE
            }
        }
        binding.layFormSex.edtSarmayesh.addTextChangedListener {
            if (pattern.matcher(binding.layFormSex.edtSarmayesh.text.toString()).find()) {
                binding.layFormSex.layWarning10.visibility = View.VISIBLE
                binding.layFormSex.imgWarring10.animation = zAnim
                binding.layFormSex.txtWarring10.text =
                    resources.getString(R.string.txt_selection_sarmayesh)
                binding.layFormSex.edtSarmayesh.setText("")

            } else {
                binding.layFormSex.layWarning10.visibility = View.GONE
            }
        }

    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
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

                binding.layFormFiveFive.edtTypeUser.setText("")
                binding.layFormFiveFive.edtTypeSanad.setText("")
                binding.layFormFiveFive.edtLocation.setText("")
                binding.layFormFiveFive.edtLocation.setText("")
                binding.layFormFiveFive.edtTabaghe.setText("")
                binding.layFormFiveFive.edtAgeBana.setText("")
                binding.layFormFiveFive.edtVaziyadMelk.setText("")
                binding.layFormFiveFive.edtCunterOtagh.setText("")
                binding.layFormFiveFive.edtNemaSakhteman.setText("")
                binding.layFormFiveFive.radioBtn1Lay5.isChecked = false
                binding.layFormFiveFive.radioBtn2Lay5.isChecked = false
                binding.layFormFiveFive.edtDescription.setText("")

                binding.layFormSex.edtCounterAllTabaghat.setText("")
                binding.layFormSex.edtCounterAllVahedha.setText("")
                binding.layFormSex.edtCounterVahedhaDarTabaghe.setText("")
                binding.layFormSex.edtJahatSakhteman.setText("")
                binding.layFormSex.edtJahatVahed.setText("")
                binding.layFormSex.edtJensKaf.setText("")
                binding.layFormSex.edtCabinets.setText("")
                binding.layFormSex.edtWc.setText("")
                binding.layFormSex.edtGarmayesh.setText("")
                binding.layFormSex.edtSarmayesh.setText("")


                binding.layScroll.visibility = View.VISIBLE
                binding.layScroll2.visibility = View.GONE
                binding.layScroll3.visibility = View.GONE
                binding.layScroll4.visibility = View.GONE
                binding.layScroll5.visibility = View.GONE
                binding.layScroll55.visibility = View.GONE
                binding.layScroll6.visibility = View.GONE
                binding.layScroll7.visibility = View.GONE
                binding.layScroll8.visibility = View.GONE

                binding.circularProgressBar.progress = 12.5.toInt()
                binding.txtTop.text = "1"
                binding.txtPageTitleTop.text =
                    resources.getString(R.string.txt_info_paye_registering)
                binding.txtPageTitleBottom.text =
                    "بعدی : ${resources.getString(R.string.txt_information_owner)}"
                binding.layDes2.visibility = View.VISIBLE
                binding.img.background = resources.getDrawable(R.drawable.document_text)
                binding.txtPageTitle.text =
                    resources.getString(R.string.txt_info_paye_registering)

                binding.layFormOne.layWarning1.visibility = View.GONE
                binding.btnPrevious.visibility = View.GONE

            }

            2 -> {
                counterStepsOne = 2
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
                binding.layScroll2.visibility = View.GONE
                binding.layScroll3.visibility = View.GONE
                binding.layScroll4.visibility = View.GONE
                binding.layScroll5.visibility = View.GONE

                binding.circularProgressBar.progress = 20
                binding.txtTop.text = "1"
                binding.txtPageTitleTop.text =
                    resources.getString(R.string.txt_info_paye_registering)
                binding.txtPageTitleBottom.text =
                    "بعدی : ${resources.getString(R.string.txt_information_owner)}"
                binding.layDes2.visibility = View.VISIBLE
                binding.img.background = resources.getDrawable(R.drawable.document_text)
                binding.txtPageTitle.text =
                    resources.getString(R.string.txt_info_paye_registering)

                binding.layFormOne.layWarning1.visibility = View.GONE
                binding.btnPrevious.visibility = View.GONE

            }

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
