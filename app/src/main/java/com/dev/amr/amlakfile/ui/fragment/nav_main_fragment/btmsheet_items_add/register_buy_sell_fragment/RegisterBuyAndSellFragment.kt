package com.dev.amr.amlakfile.ui.fragment.nav_main_fragment.btmsheet_items_add.register_buy_sell_fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.Navigation
import androidx.room.Room
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.base.BaseFragment
import com.dev.amr.amlakfile.base.BaseLiveDialog
import com.dev.amr.amlakfile.data.db.DBRoom
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.data.model.custom_views.IEditText
import com.dev.amr.amlakfile.data.model.custom_views.ITextView
import com.dev.amr.amlakfile.data.model.model.JDF
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModel
import com.dev.amr.amlakfile.databinding.FragmentRegisterBuyAndSellBinding
import com.dev.amr.amlakfile.ui.btmSheetDialog.BtmSheetKharidForoshDialog
import com.dev.amr.amlakfile.utils.NumberTextWatcher
import com.github.yamin8000.ppn.PersianDigits
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.hours


class RegisterBuyAndSellFragment : BaseFragment(), View.OnClickListener,
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: FragmentRegisterBuyAndSellBinding
    private lateinit var countGetList: Number
    private var cameraRequest: Int = 0
    private lateinit var cameraIntent: Intent
    private lateinit var db: DBRoom
    private lateinit var animation: Animation

    private lateinit var user: String
    private lateinit var ownerName: String
    private lateinit var ownerPhone: String
    private lateinit var cabinets: String
    private lateinit var addressFile: String
    private lateinit var description: String
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
    private lateinit var metrazhMoraba: String
    private lateinit var location: String
    private lateinit var ageBana: String
    private lateinit var tabaghe: String
    private lateinit var cunterOtagh: String
    private lateinit var vaziyatMelk: String
    private lateinit var priceMelk: String
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

    private lateinit var calendar : Calendar
    private val TIME_PICKER = "TimeBtnPickerDialog"
    private val DATE_PICKER = "DatePickerDialog"

//    private  var day : Int =0
//    private  var month : Int =0
//    private  var year : Int =0

    private var btmSheetKharidForosh: BtmSheetKharidForoshDialog = BtmSheetKharidForoshDialog()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBuyAndSellBinding.inflate(layoutInflater)
        onClickViews()

        db = Room.databaseBuilder(requireActivity(), DBRoom::class.java, "amlak_db")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

        calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH+1)
        val year = calendar.get(Calendar.YEAR)

        val hour = calendar.get(Calendar.HOUR).hours
        val minute = calendar.get(Calendar.MINUTE)

        val resultDate = "$year / $month / $day"

getCurrentDate()
//        val sdf = SimpleDateFormat("yyyy MMM dd")
//        val calendar: Calendar = GregorianCalendar(year, month, day)
//        binding.edtDate.setText(calendar)

        val resultTime = "$hour : $minute"
        binding.edtTime.setText(resultTime)


        return binding.root
    }

    private fun onClickViews() {
        binding.txtTitle.text = getString(R.string.txt_register_file_kharid_forosh)
        binding.toolbar.btnMenu.visibility = View.GONE
        binding.btnCancel.setOnClickListener(this)
        binding.laySave.setOnClickListener(this)
        binding.edtTypeUser.setOnClickListener(this)
        binding.edtTypeSanad.setOnClickListener(this)
        binding.edtAgeBana.setOnClickListener(this)
        binding.edtTabaghe.setOnClickListener(this)
        binding.edtCunterOtagh.setOnClickListener(this)
        binding.edtVaziyadMelk.setOnClickListener(this)
        binding.edtWc.setOnClickListener(this)
        binding.edtNemaSakhteman.setOnClickListener(this)
        binding.edtCabinets.setOnClickListener(this)
        binding.edtJahatSakhteman.setOnClickListener(this)
        binding.edtJahatVahed.setOnClickListener(this)
        binding.edtJensKaf.setOnClickListener(this)
        binding.edtGarmayesh.setOnClickListener(this)
        binding.edtSarmayesh.setOnClickListener(this)
        binding.layImg1.setOnClickListener(this)
        binding.img1.setOnClickListener(this)
        binding.txtImg1.setOnClickListener(this)
        binding.layImg2.setOnClickListener(this)
        binding.img2.setOnClickListener(this)
        binding.txtImg2.setOnClickListener(this)
        binding.layImg3.setOnClickListener(this)
        binding.img3.setOnClickListener(this)
        binding.txtImg3.setOnClickListener(this)
        binding.layImg4.setOnClickListener(this)
        binding.img4.setOnClickListener(this)
        binding.txtImg4.setOnClickListener(this)
        binding.layImg5.setOnClickListener(this)
        binding.img5.setOnClickListener(this)
        binding.txtImg5.setOnClickListener(this)
        binding.layImg6.setOnClickListener(this)
        binding.img6.setOnClickListener(this)
        binding.txtImg6.setOnClickListener(this)
        binding.btnImgDelete1.setOnClickListener(this)
        binding.btnImgDelete2.setOnClickListener(this)
        binding.btnImgDelete3.setOnClickListener(this)
        binding.btnImgDelete4.setOnClickListener(this)
        binding.btnImgDelete5.setOnClickListener(this)
        binding.btnImgDelete6.setOnClickListener(this)
        binding.checkSelectAll.setOnClickListener(this)
        binding.btnShowItems.setOnClickListener(this)
        binding.imgShowItems.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BaseLiveDialog.liveDataTypeUser.observe(requireActivity()) {
            try {
                when (Hawks.getInt("countGetList")) {
                    1 -> {
                        binding.edtTypeUser.error = null
                        binding.edtTypeUser.setText(Hawks.getString("item"))
                    }

                    2 -> {
                        binding.edtTypeSanad.error = null
                        binding.edtTypeSanad.setText(Hawks.getString("item"))
                    }

                    3 -> {
                        binding.edtAgeBana.error = null
                        binding.edtAgeBana.setText(Hawks.getString("item"))
                    }

                    4 -> {
                        binding.edtTabaghe.error = null
                        binding.edtTabaghe.setText(Hawks.getString("item"))
                    }

                    5 -> {
                        binding.edtCunterOtagh.error = null
                        binding.edtCunterOtagh.setText(Hawks.getString("item"))
                    }

                    6 -> {
                        binding.edtVaziyadMelk.error = null
                        binding.edtVaziyadMelk.setText(Hawks.getString("item"))
                    }

                    7 -> {
                        binding.edtWc.error = null
                        binding.edtWc.setText(Hawks.getString("item"))
                    }

                    8 -> {
                        binding.edtNemaSakhteman.error = null
                        binding.edtNemaSakhteman.setText(Hawks.getString("item"))
                    }

                    9 -> {
                        binding.edtCabinets.error = null
                        binding.edtCabinets.setText(Hawks.getString("item"))
                    }

                    10 -> {
                        binding.edtJahatSakhteman.error = null
                        binding.edtJahatSakhteman.setText(Hawks.getString("item"))
                    }

                    11 -> {
                        binding.edtJahatVahed.error = null
                        binding.edtJahatVahed.setText(Hawks.getString("item"))
                    }

                    12 -> {
                        binding.edtJensKaf.error = null
                        binding.edtJensKaf.setText(Hawks.getString("item"))
                    }

                    13 -> {
                        binding.edtGarmayesh.error = null
                        binding.edtGarmayesh.setText(Hawks.getString("item"))
                    }

                    14 -> {
                        binding.edtSarmayesh.error = null
                        binding.edtSarmayesh.setText(Hawks.getString("item"))
                    }
                }
                btmSheetKharidForosh.dismiss()
            } catch (e: Exception) {
                e.message
            }
        }

        BaseLiveDialog.liveDataBackToHomePage.observe(requireActivity()) {
            if (it != null){
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_registerBuyAndSellFragment_to_homeFragment)
            }
        }

        BaseLiveDialog.liveDataEmptyItems.observe(requireActivity()) {
            itemsEmpty()
        }

        val input = view.findViewById<IEditText>(R.id.edt_price_melk)
        val textView = view.findViewById<ITextView>(R.id.txt_price_melk)

        input.doAfterTextChanged {
            if (input.length() == 0) {
                textView.visibility = View.GONE
            } else if (input.length() != 0) {
                textView.visibility = View.VISIBLE
                val number = it.toString()
                textView.text = PersianDigits.spellToPersian(number.replace(",", "")) + " تومان"
            }
        }

        input.addTextChangedListener(NumberTextWatcher(input))
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED
        )

            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.CAMERA), cameraRequest
            )

    }

    private fun itemsEmpty() {
        binding.edtUserAdded.setText("")
        binding.edtOwnerName.setText("")
        binding.edtOwnerPhone.setText("")
        binding.edtCabinets.setText("")
        binding.edtJahatSakhteman.setText("")
        binding.edtJahatVahed.setText("")
        binding.edtJensKaf.setText("")
        binding.edtGarmayesh.setText("")
        binding.edtSarmayesh.setText("")
        binding.edtAddressFile.setText("")
        binding.edtDescription.setText("")
        binding.edtCounterAllTabaghat.setText("")
        binding.edtCounterAllVahedha.setText("")
        binding.edtCounterVahedhaDarTabaghe.setText("")
        binding.toolbar.txtTitle.requestFocus()
        binding.edtTypeUser.setText("")
        binding.edtTypeSanad.setText("")
        binding.edtMetrazhMoraba.setText("")
        binding.edtLocation.setText("")
        binding.edtAgeBana.setText("")
        binding.edtTabaghe.setText("")
        binding.edtCunterOtagh.setText("")
        binding.edtVaziyadMelk.setText("")
        binding.edtPriceMelk.setText("")
        binding.edtWc.setText("")
        binding.edtNemaSakhteman.setText("")

        binding.checkSelectAll.isChecked = false
        binding.checkAnbari.isChecked = false
        binding.checkAsansor.isChecked = false
        binding.checkBalkon.isChecked = false
        binding.checkParking.isChecked = false
        binding.checkIphonTasviri.isChecked = false
        binding.checkEstakhr.isChecked = false
        binding.checkSona.isChecked = false
        binding.checkGazRomizi.isChecked = false
        binding.checkHod.isChecked = false
        binding.checkKomodDivari.isChecked = false
        binding.checkDarbZedSerghat.isChecked = false
        binding.checkHayat.isChecked = false
        binding.checkJakozi.isChecked = false
        binding.checkLabi.isChecked = false
        binding.checkSalonVarzeshi.isChecked = false
        binding.checkSalonEjtemaat.isChecked = false
        binding.checkAntenMarkazi.isChecked = false
        binding.checkJarobarghiMarkazi.isChecked = false
        binding.checkMelkBazsaziShode.isChecked = false

        binding.img1.visibility = View.VISIBLE
        binding.txtImg1.visibility = View.VISIBLE
        binding.image1.visibility = View.GONE
        binding.btnImgDelete1.visibility = View.GONE
        binding.image1.setImageBitmap(null)

        binding.img2.visibility = View.VISIBLE
        binding.txtImg2.visibility = View.VISIBLE
        binding.image2.visibility = View.GONE
        binding.btnImgDelete2.visibility = View.GONE
        binding.image2.setImageBitmap(null)

        binding.img3.visibility = View.VISIBLE
        binding.txtImg3.visibility = View.VISIBLE
        binding.image3.visibility = View.GONE
        binding.btnImgDelete3.visibility = View.GONE
        binding.image3.setImageBitmap(null)

        binding.img4.visibility = View.VISIBLE
        binding.txtImg4.visibility = View.VISIBLE
        binding.image4.visibility = View.GONE
        binding.btnImgDelete4.visibility = View.GONE
        binding.image4.setImageBitmap(null)

        binding.img5.visibility = View.VISIBLE
        binding.txtImg5.visibility = View.VISIBLE
        binding.image5.visibility = View.GONE
        binding.btnImgDelete5.visibility = View.GONE
        binding.image5.setImageBitmap(null)

        binding.img6.visibility = View.VISIBLE
        binding.txtImg6.visibility = View.VISIBLE
        binding.image6.visibility = View.GONE
        binding.btnImgDelete6.visibility = View.GONE
        binding.image6.setImageBitmap(null)

    }

    override fun onClick(v: View?) {
        cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        when (v?.id) {
            R.id.btn_cancel -> {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_registerBuyAndSellFragment_to_homeFragment)
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

            R.id.img_1,
            R.id.txt_img_1 -> {
//                cameraRequest = 1
//                startActivityForResult(cameraIntent, cameraRequest)
                showDialogInactive(requireActivity())
            }

            R.id.img_2,
            R.id.txt_img_2 -> {
//                cameraRequest = 2
//                startActivityForResult(cameraIntent, cameraRequest)
                showDialogInactive(requireActivity())
            }

            R.id.img_3,
            R.id.txt_img_3 -> {
//                cameraRequest = 3
//                startActivityForResult(cameraIntent, cameraRequest)
                showDialogInactive(requireActivity())
            }

            R.id.img_4,
            R.id.txt_img_4 -> {
//                cameraRequest = 4
//                startActivityForResult(cameraIntent, cameraRequest)
                showDialogInactive(requireActivity())
            }

            R.id.img_5,
            R.id.txt_img_5 -> {
//                cameraRequest = 5
//                startActivityForResult(cameraIntent, cameraRequest)
                showDialogInactive(requireActivity())
            }

            R.id.img_6,
            R.id.txt_img_6 -> {
//                cameraRequest = 6
//                startActivityForResult(cameraIntent, cameraRequest)
                showDialogInactive(requireActivity())
            }

            R.id.btn_img_delete1 -> {
                binding.img1.visibility = View.VISIBLE
                binding.txtImg1.visibility = View.VISIBLE
                binding.image1.visibility = View.GONE
                binding.btnImgDelete1.visibility = View.GONE
                binding.image1.setImageBitmap(null)
            }

            R.id.btn_img_delete2 -> {
                binding.img2.visibility = View.VISIBLE
                binding.txtImg2.visibility = View.VISIBLE
                binding.image2.visibility = View.GONE
                binding.btnImgDelete2.visibility = View.GONE
                binding.image2.setImageBitmap(null)
            }

            R.id.btn_img_delete3 -> {
                binding.img3.visibility = View.VISIBLE
                binding.txtImg3.visibility = View.VISIBLE
                binding.image3.visibility = View.GONE
                binding.btnImgDelete3.visibility = View.GONE
                binding.image3.setImageBitmap(null)
            }

            R.id.btn_img_delete4 -> {
                binding.img4.visibility = View.VISIBLE
                binding.txtImg4.visibility = View.VISIBLE
                binding.image4.visibility = View.GONE
                binding.btnImgDelete4.visibility = View.GONE
                binding.image4.setImageBitmap(null)
            }

            R.id.btn_img_delete5 -> {
                binding.img5.visibility = View.VISIBLE
                binding.txtImg5.visibility = View.VISIBLE
                binding.image5.visibility = View.GONE
                binding.btnImgDelete5.visibility = View.GONE
                binding.image5.setImageBitmap(null)
            }

            R.id.btn_img_delete6 -> {
                binding.img6.visibility = View.VISIBLE
                binding.txtImg6.visibility = View.VISIBLE
                binding.image6.visibility = View.GONE
                binding.btnImgDelete6.visibility = View.GONE
                binding.image6.setImageBitmap(null)
            }

            R.id.check_select_all -> {
                if (binding.checkSelectAll.isChecked) {
                    binding.checkAnbari.isChecked = true
                    binding.checkAsansor.isChecked = true
                    binding.checkBalkon.isChecked = true
                    binding.checkParking.isChecked = true
                    binding.checkIphonTasviri.isChecked = true
                    binding.checkEstakhr.isChecked = true
                    binding.checkSona.isChecked = true
                    binding.checkGazRomizi.isChecked = true
                    binding.checkHod.isChecked = true
                    binding.checkDarbZedSerghat.isChecked = true
                    binding.checkHayat.isChecked = true
                    binding.checkJakozi.isChecked = true
                    binding.checkLabi.isChecked = true
                    binding.checkSalonVarzeshi.isChecked = true
                    binding.checkSalonEjtemaat.isChecked = true
                    binding.checkAntenMarkazi.isChecked = true
                    binding.checkJarobarghiMarkazi.isChecked = true
                    binding.checkKomodDivari.isChecked = true
                    binding.checkMelkBazsaziShode.isChecked = true
                } else {
                    binding.checkAnbari.isChecked = false
                    binding.checkAsansor.isChecked = false
                    binding.checkBalkon.isChecked = false
                    binding.checkParking.isChecked = false
                    binding.checkIphonTasviri.isChecked = false
                    binding.checkEstakhr.isChecked = false
                    binding.checkSona.isChecked = false
                    binding.checkGazRomizi.isChecked = false
                    binding.checkHod.isChecked = false
                    binding.checkDarbZedSerghat.isChecked = false
                    binding.checkHayat.isChecked = false
                    binding.checkJakozi.isChecked = false
                    binding.checkLabi.isChecked = false
                    binding.checkSalonVarzeshi.isChecked = false
                    binding.checkSalonEjtemaat.isChecked = false
                    binding.checkAntenMarkazi.isChecked = false
                    binding.checkJarobarghiMarkazi.isChecked = false
                    binding.checkKomodDivari.isChecked = false
                    binding.checkMelkBazsaziShode.isChecked = false
                }
            }

            R.id.img_show_items,
            R.id.btn_show_items -> {
                if (binding.layAnbari.visibility == View.GONE){
                    animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
                    binding.layItemsRadio1.animation = animation
                    binding.layItemsRadio2.animation = animation
                    binding.btnShowItems.setText(R.string.txt_show_items_fewer)
                    binding.imgShowItems.rotation = 90F
                    binding.layAnbari.visibility = View.VISIBLE
                    binding.layBalkon.visibility = View.VISIBLE
                    binding.laySona.visibility = View.VISIBLE
                    binding.layGazRomizi.visibility = View.VISIBLE
                    binding.layHod.visibility = View.VISIBLE
                    binding.layDarbZedSerghat.visibility = View.VISIBLE
                    binding.layKomodDivari.visibility = View.VISIBLE
                    binding.layHayat.visibility = View.VISIBLE
                    binding.layEstakhr.visibility = View.VISIBLE
                    binding.layIphonTasviri.visibility = View.VISIBLE
                    binding.layLabi.visibility = View.VISIBLE
                    binding.laySalonVarzeshi.visibility = View.VISIBLE
                    binding.laySalonEjtemaat.visibility = View.VISIBLE
                    binding.layAntenMarkazi.visibility = View.VISIBLE
                    binding.layJarobarghiMarkazi.visibility = View.VISIBLE
                    binding.laySelectAll.visibility = View.VISIBLE
                }else if (binding.layAnbari.visibility == View.VISIBLE){
                    animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up

                    )
                    binding.layItemsRadio1.animation = animation
                    binding.layItemsRadio2.animation = animation
                    binding.btnShowItems.setText(R.string.txt_show_items_More)
                    binding.imgShowItems.rotation = 270F
                    binding.layAnbari.visibility = View.GONE
                    binding.layBalkon.visibility = View.GONE
                    binding.laySona.visibility = View.GONE
                    binding.layGazRomizi.visibility = View.GONE
                    binding.layHod.visibility = View.GONE
                    binding.layDarbZedSerghat.visibility = View.GONE
                    binding.layKomodDivari.visibility = View.GONE
                    binding.layHayat.visibility = View.GONE
                    binding.layEstakhr.visibility = View.GONE
                    binding.layIphonTasviri.visibility = View.GONE
                    binding.layLabi.visibility = View.GONE
                    binding.laySalonVarzeshi.visibility = View.GONE
                    binding.laySalonEjtemaat.visibility = View.GONE
                    binding.layAntenMarkazi.visibility = View.GONE
                    binding.layJarobarghiMarkazi.visibility = View.GONE
                    binding.laySelectAll.visibility = View.GONE
                }
            }

            R.id.lay_save -> {

                if (binding.edtUserAdded.text.toString() == "" ||
                    binding.edtOwnerName.text.toString() == "" ||
                    binding.edtOwnerPhone.text.toString() == "" ||
                    binding.edtCabinets.text.toString() == "" ||
                    binding.edtJahatSakhteman.text.toString() == "" ||
                    binding.edtJahatVahed.text.toString() == "" ||
                    binding.edtJensKaf.text.toString() == "" ||
                    binding.edtGarmayesh.text.toString() == "" ||
                    binding.edtSarmayesh.text.toString() == "" ||
                    binding.edtAddressFile.text.toString() == "" ||
                    binding.edtDescription.text.toString() == "" ||
                    binding.edtCounterAllTabaghat.text.toString() == "" ||
                    binding.edtCounterAllVahedha.text.toString() == "" ||
                    binding.edtCounterVahedhaDarTabaghe.text.toString() == "" ||
                    binding.edtTypeUser.text.toString() == "" ||
                    binding.edtTypeSanad.text.toString() == "" ||
                    binding.edtMetrazhMoraba.text.toString() == "" ||
                    binding.edtLocation.text.toString() == "" ||
                    binding.edtAgeBana.text.toString() == "" ||
                    binding.edtTabaghe.text.toString() == "" ||
                    binding.edtCunterOtagh.text.toString() == "" ||
                    binding.edtVaziyadMelk.text.toString() == "" ||
                    binding.edtPriceMelk.text.toString() == "" ||
                    binding.edtWc.text.toString() == "" ||
                    binding.edtNemaSakhteman.text.toString() == ""
                ) {

//                    showDialogEmpty(requireActivity())

                    if (binding.edtUserAdded.text.toString() == "") {
                        binding.edtUserAdded.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtOwnerName.text.toString() == "") {
                        binding.edtOwnerName.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtOwnerPhone.text.toString() == "") {
                        binding.edtOwnerPhone.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtCabinets.text.toString() == "") {
                        binding.edtCabinets.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtJahatSakhteman.text.toString() == "") {
                        binding.edtJahatSakhteman.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtJahatVahed.text.toString() == "") {
                        binding.edtJahatVahed.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtJensKaf.text.toString() == "") {
                        binding.edtJensKaf.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtGarmayesh.text.toString() == "") {
                        binding.edtGarmayesh.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtSarmayesh.text.toString() == "") {
                        binding.edtSarmayesh.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtAddressFile.text.toString() == "") {
                        binding.edtAddressFile.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtDescription.text.toString() == "") {
                        binding.edtDescription.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtCounterAllTabaghat.text.toString() == "") {
                        binding.edtCounterAllTabaghat.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtCounterAllVahedha.text.toString() == "") {
                        binding.edtCounterAllVahedha.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtCounterVahedhaDarTabaghe.text.toString() == "") {
                        binding.edtCounterVahedhaDarTabaghe.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtTypeUser.text.toString() == "") {
                        binding.edtTypeUser.error = " "
                    }
                    if (binding.edtTypeSanad.text.toString() == "") {
                        binding.edtTypeSanad.error = " "
                    }
                    if (binding.edtMetrazhMoraba.text.toString() == "") {
                        binding.edtMetrazhMoraba.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtLocation.text.toString() == "") {
                        binding.edtLocation.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtAgeBana.text.toString() == "") {
                        binding.edtAgeBana.error = " "
                    }
                    if (binding.edtTabaghe.text.toString() == "") {
                        binding.edtTabaghe.error = " "
                    }
                    if (binding.edtCunterOtagh.text.toString() == "") {
                        binding.edtCunterOtagh.error = " "
                    }
                    if (binding.edtVaziyadMelk.text.toString() == "") {
                        binding.edtVaziyadMelk.error = " "
                    }
                    if (binding.edtPriceMelk.text.toString() == "") {
                        binding.edtPriceMelk.error = getString(R.string.txt_ejbari_mibashad)
                    }
                    if (binding.edtWc.text.toString() == "") {
                        binding.edtWc.error = " "
                    }
                    if (binding.edtNemaSakhteman.text.toString() == "") {
                        binding.edtNemaSakhteman.error = " "
                    }

                } else {
                    user = binding.edtUserAdded.text.toString()
                    ownerName = binding.edtOwnerName.text.toString()
                    ownerPhone = binding.edtOwnerPhone.text.toString()
                    cabinets = binding.edtCabinets.text.toString()
                    jahatSakhteman = binding.edtJahatSakhteman.text.toString()
                    jahatVahed = binding.edtJahatVahed.text.toString()
                    jensKaf = binding.edtJensKaf.text.toString()
                    garmayesh = binding.edtGarmayesh.text.toString()
                    sarmayesh = binding.edtSarmayesh.text.toString()
                    addressFile = binding.edtAddressFile.text.toString()
                    description = binding.edtDescription.text.toString()
                    counterAllTabaghat = binding.edtCounterAllTabaghat.text.toString()
                    counterAllVahedha = binding.edtCounterAllVahedha.text.toString()
                    counterVahedhaDarTabaghe = binding.edtCounterVahedhaDarTabaghe.text.toString()
                    typeUser = binding.edtTypeUser.text.toString()
                    typeSanad = binding.edtTypeSanad.text.toString()
                    metrazhMoraba = binding.edtMetrazhMoraba.text.toString() + "متر"
                    location = binding.edtLocation.text.toString()
                    ageBana = binding.edtAgeBana.text.toString()
                    tabaghe = binding.edtTabaghe.text.toString()
                    cunterOtagh = binding.edtCunterOtagh.text.toString()
                    vaziyatMelk = binding.edtVaziyadMelk.text.toString()
                    priceMelk = binding.edtPriceMelk.text.toString() + " , " +
                            binding.txtPriceMelk.text.toString()
                    wC = binding.edtWc.text.toString()
                    nemaSakhteman = binding.edtNemaSakhteman.text.toString()

                    checkAsansor = if (binding.checkAsansor.isChecked) {
                        binding.checkAsansor.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkMelkBazsaziShode = if (binding.checkMelkBazsaziShode.isChecked) {
                        binding.checkMelkBazsaziShode.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkAnbari = if (binding.checkAnbari.isChecked) {
                        binding.checkAnbari.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkBalkon = if (binding.checkBalkon.isChecked) {
                        binding.checkBalkon.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkSona = if (binding.checkSona.isChecked) {
                        binding.checkSona.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkGazRomizi = if (binding.checkGazRomizi.isChecked) {
                        binding.checkGazRomizi.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkHod = if (binding.checkHod.isChecked) {
                        binding.checkHod.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkDarbZedSerghat = if (binding.checkDarbZedSerghat.isChecked) {
                        binding.checkDarbZedSerghat.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkKomodDivari = if (binding.checkKomodDivari.isChecked) {
                        binding.checkKomodDivari.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkHayat = if (binding.checkHayat.isChecked) {
                        binding.checkHayat.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkParking = if (binding.checkParking.isChecked) {
                        binding.checkParking.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkJakozi = if (binding.checkJakozi.isChecked) {
                        binding.checkJakozi.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkEstakhr = if (binding.checkEstakhr.isChecked) {
                        binding.checkEstakhr.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkIphonTasviri = if (binding.checkIphonTasviri.isChecked) {
                        binding.checkIphonTasviri.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkLabi = if (binding.checkLabi.isChecked) {
                        binding.checkLabi.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkSalonVarzeshi = if (binding.checkSalonVarzeshi.isChecked) {
                        binding.checkSalonVarzeshi.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkSalonEjtemaat = if (binding.checkSalonEjtemaat.isChecked) {
                        binding.checkSalonEjtemaat.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkAntenMarkazi = if (binding.checkAntenMarkazi.isChecked) {
                        binding.checkAntenMarkazi.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    checkJarobarghiMarkazi = if (binding.checkJarobarghiMarkazi.isChecked) {
                        binding.checkJarobarghiMarkazi.tag.toString()
                    } else {
                        getString(R.string.txt_does_not_have)
                    }

                    if (binding.checkSelectAll.isChecked) {
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

                    val registerBuyAndSell = RegisterBuyAndSellModel(
                        0, user, ownerName, ownerPhone, cabinets, addressFile, description,
                        counterAllTabaghat,counterAllVahedha,counterVahedhaDarTabaghe,jahatSakhteman,
                        jahatVahed,jensKaf,garmayesh,sarmayesh,
                        typeUser, typeSanad, metrazhMoraba, location, ageBana,
                        tabaghe, cunterOtagh, vaziyatMelk, priceMelk, wC, nemaSakhteman,
                        checkAsansor, checkMelkBazsaziShode, checkAnbari, checkParking, checkEstakhr,
                        checkIphonTasviri, checkBalkon, checkSona, checkGazRomizi, checkHod, checkDarbZedSerghat,
                        checkKomodDivari, checkHayat, checkJakozi, checkLabi, checkSalonVarzeshi,
                        checkSalonEjtemaat, checkAntenMarkazi, checkJarobarghiMarkazi, checkSelectAll,
                        photo1, photo1, photo1, photo1, photo1, photo1
                    )

                    val result = db.dBDao().upsertRegisterBuyAndSell(registerBuyAndSell)
                    if (result > 0) {
                        showDialogDoYouContinue(requireActivity())
//                        agreeDialog()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.txt_save_infonmation_failure), Toast.LENGTH_LONG
                        ).show()
                    }
                }


            }

            R.id.edt_time -> {
//                val nowtime = PersianCalendar()
//                nowtime.getPersianShortDateTime()
//                val date = Date()
//                val hourString = if (date.hours < 10) "0" + date.hours else "" + date.hours
//                val minuteString = if (date.minutes < 10) "0" + date.minutes else "" + date.minutes
//                val tpd: TimePickerDialog = TimePickerDialog.newInstance(
//                    requireActivity(), hourString.toInt(), minuteString.toInt(),
//                    true
//                )
//
//                tpd.show(requireActivity().supportFragmentManager, TIME_PICKER)
            }

            R.id.edt_date -> {
//                val now = PersianCalendar()
//                val dpd: DatePickerDialog = DatePickerDialog.newInstance(
//                    requireActivity(),
//                    now.getPersianYear(),
//                    now.getPersianMonth(),
//                    now.getPersianDay()
//                )
//
//                dpd.show(requireActivity().supportFragmentManager,DATE_PICKER)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap

            when (cameraRequest) {
                1 -> {
                    binding.img1.visibility = View.GONE
                    binding.txtImg1.visibility = View.GONE
                    binding.image1.visibility = View.VISIBLE
                    binding.btnImgDelete1.visibility = View.VISIBLE
                    binding.image1.setImageBitmap(photo)
                    photo1 = bitMapToString(photo)
                }

                2 -> {
                    binding.img2.visibility = View.GONE
                    binding.txtImg2.visibility = View.GONE
                    binding.image2.visibility = View.VISIBLE
                    binding.btnImgDelete2.visibility = View.VISIBLE
                    binding.image2.setImageBitmap(photo)
                    photo2 = bitMapToString(photo)
                }

                3 -> {
                    binding.img3.visibility = View.GONE
                    binding.txtImg3.visibility = View.GONE
                    binding.image3.visibility = View.VISIBLE
                    binding.btnImgDelete3.visibility = View.VISIBLE
                    binding.image3.setImageBitmap(photo)
                    photo3 = bitMapToString(photo)
                }

                4 -> {
                    binding.img4.visibility = View.GONE
                    binding.txtImg4.visibility = View.GONE
                    binding.image4.visibility = View.VISIBLE
                    binding.btnImgDelete4.visibility = View.VISIBLE
                    binding.image4.setImageBitmap(photo)
                    photo4 = bitMapToString(photo)
                }

                5 -> {
                    binding.img5.visibility = View.GONE
                    binding.txtImg5.visibility = View.GONE
                    binding.image5.visibility = View.VISIBLE
                    binding.btnImgDelete5.visibility = View.VISIBLE
                    binding.image5.setImageBitmap(photo)
                    photo5 = bitMapToString(photo)
                }

                6 -> {
                    binding.img6.visibility = View.GONE
                    binding.txtImg6.visibility = View.GONE
                    binding.image6.visibility = View.VISIBLE
                    binding.btnImgDelete6.visibility = View.VISIBLE
                    binding.image6.setImageBitmap(photo)
                    photo6 = bitMapToString(photo)
                }
            }

        }

    }


    private fun agreeDialog() {
        var alertDialogBuilder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
        val li = LayoutInflater.from(context)
        @SuppressLint("InflateParams") val promptsView: View =
            li.inflate(R.layout.dialog_do_you_continue_layout, null)


        promptsView.background = resources.getDrawable(R.color.transparent)
        alertDialogBuilder.setView(promptsView)
        alertDialogBuilder.setCancelable(true)
        val alertDialog = alertDialogBuilder.create()

        alertDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        var stringOfDate = year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth
        val month: Int = monthOfYear + 1
        val newmonth: String
        val newday: String
        newmonth = if (month < 10) {
            "0$month"
        } else {
            month.toString()
        }
        newday = if (dayOfMonth < 10) {
            "0$dayOfMonth"
        } else {
            dayOfMonth.toString()
        }
        stringOfDate = "$year/$newmonth/$newday"
        binding.edtDate.setText("")
        binding.edtDate.setText(stringOfDate)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val hourString = if (hourOfDay < 10) "0$hourOfDay" else "" + hourOfDay
        val minuteString = if (minute < 10) "0$minute" else "" + minute
        val time = "$hourString:$minuteString"
        binding.edtTime.setText("")
        binding.edtTime.setText(time)
    }

    private var newMonth: String? = null
    private var newDay: String? = null
    private var month = 0
    private var day = 0
    @SuppressLint("SetTextI18n")
    private fun getCurrentDate() {
        val jdf = JDF()
        val date = Date()
        month = jdf.getIranianMonth()
        day = jdf.getIranianDay()
        if (month < 10) {
            newMonth = "0" + jdf.getIranianMonth()
        } else {
            newMonth = java.lang.String.valueOf(jdf.getIranianMonth())
        }
        if (day < 10) {
            newDay = "0$day"
        } else {
            newDay = day.toString()
        }
        val hourString = if (date.hours < 10) "0" + date.hours else "" + date.hours
        val minuteString = if (date.minutes < 10) "0" + date.minutes else "" + date.minutes
        val time = "$hourString:$minuteString"
        binding.edtDate.setText("${jdf.iranianYear} / $newMonth / + $newDay")
        binding.edtTime.setText("$hourString:$minuteString")
    }

}
