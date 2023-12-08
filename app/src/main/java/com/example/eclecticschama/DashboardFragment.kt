package com.example.eclecticschama

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticschama.adapter.CategoryAdapter
import com.example.eclecticschama.adapter.UpcomingAdapter
import com.example.eclecticschama.data.CategoryItem
import com.example.eclecticschama.data.UpcomingItem
import com.example.eclecticschama.databinding.FragmentDashboardBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date

import java.util.Locale
import kotlin.random.Random

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adapter: CategoryAdapter

    private lateinit var adapterUpcoming: UpcomingAdapter
    lateinit var layoutManager: GridLayoutManager
    lateinit var upcomingLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater,container,false)

        displayAmounts(1200.00
        )
        createTable()
        navigateBack()


        layoutManager = GridLayoutManager(context,3, LinearLayoutManager.VERTICAL,false)
        adapter = CategoryAdapter(createDashboardList()
        )
        binding.categoryRecyclerView.adapter = adapter
        binding.categoryRecyclerView.layoutManager =layoutManager


        adapterUpcoming = UpcomingAdapter(createUpcomingList())
        binding.recyUpcoming.adapter = adapterUpcoming


        upcomingLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyUpcoming.layoutManager =upcomingLayoutManager

        // Attach PagerSnapHelper to the RecyclerView
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.recyUpcoming)




        return binding.root
    }


    private fun createDashboardList(): List<CategoryItem> {

        return listOf(
            CategoryItem(R.drawable.bank, "Bank Account"),
            CategoryItem(R.drawable.loan, "Loans"),
            CategoryItem(R.drawable.stamp, "My Approvals"),
            CategoryItem(R.drawable.edit, "Edit Group"),
            CategoryItem(R.drawable.group, "Members"),
            CategoryItem(R.drawable.atm, "Withdrawal"),
            // Add more items as needed
        )
    }


    private fun createUpcomingList(): MutableList<UpcomingItem> {

        return mutableListOf(
            UpcomingItem("2023-01-01", "Ksh 100", "Ksh 10"),
            UpcomingItem("2023-02-15", "Ksh 150", "Ksh 15"),
            UpcomingItem("2023-03-30", "Ksh 200", "Ksh 20"),
            UpcomingItem("2023-04-10", "Ksh 120", "Ksh 12"),
            UpcomingItem("2023-05-25", "Ksh 180", "Ksh 18")
        )
    }

    private fun createTable(){

        val tableLayout: TableLayout = binding.tableLayout

        // Create Header Row
        val headerRow = TableRow(requireContext())
        headerRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        // Set header row background color to blue
        headerRow.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))

        for (columnName in listOf("Date", "Description", "Mode", "Amount")) {
            val headerTextView = createTextView(columnName, true)
            headerRow.addView(headerTextView)
        }


        tableLayout.addView(headerRow)

        // Add Data Rows (You can loop through your data and add rows dynamically)
        for (row in 1..4) {
            val dataRow = TableRow(requireContext())
            dataRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )

            // Generate random date
            val randomDate = generateRandomDate()
            val dateTextView = createTextView(randomDate, false)
            dataRow.addView(dateTextView)


            // Fixed values for Description and Mode
            val descriptionTextView = createTextView("chama Cont.", false)
            val modeTextView = createTextView("MPESA", false)

            dataRow.addView(descriptionTextView)
            dataRow.addView(modeTextView)

            // Generate random amount
            val randomAmount = generateRandomAmount()
            val amountTextView = createTextView(randomAmount.toString(), false)
            dataRow.addView(amountTextView)

            // Set background resource for the TableRow
            dataRow.setBackgroundResource(R.drawable.table_background)

            tableLayout.addView(dataRow)
        }
        // Set stretchColumns to evenly distribute columns
        tableLayout.isStretchAllColumns

    }

    private fun createTextView(text: String, isHeader: Boolean): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT

        )

        // Set layout weight to 1 for equal distribution
        layoutParams.weight = 1f
        textView.layoutParams = layoutParams

        textView.gravity = Gravity.CENTER

        if (isHeader) {
            textView.setTextColor(resources.getColor(android.R.color.white))
            textView.textSize = 16f
            textView.setPadding(8, 8, 8, 8)
           // textView.setBackgroundResource(R.drawable.table_background)

        } else {

            
            textView.setTextColor(resources.getColor(android.R.color.darker_gray))
            textView.textSize = 14f
            textView.setPadding(8, 4, 8, 4)
            //textView.setBackgroundResource(R.drawable.table_background)
        }

        return textView
    }

    private fun generateRandomDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val randomDate = Date(Random.nextLong(0, System.currentTimeMillis()))
        return dateFormat.format(randomDate)
    }

    private fun generateRandomAmount(): Int {
        return Random.nextDouble(1.0, 100.0).toInt()

    }


    private fun navigateBack(){
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun displayAmounts(amount :Double){
        val kenyaLocale = Locale("sw", "KE")


        val formattedTip = NumberFormat.getCurrencyInstance(kenyaLocale).format(amount)
        binding.tvContFrequencyNumber.text = getString(R.string.contribution_frequency_amount,formattedTip)

        binding.paybillBalanceNumber.text    = getString(R.string.paybill_account_balance,formattedTip)
        binding.tvGroupBalanceNumber.text    = getString(R.string.group_balance_amount,formattedTip)


        val groupMembers :Int = 30

        binding.tvGroupMembersNumber.text = getString(R.string.group_members,groupMembers)

    }

}


