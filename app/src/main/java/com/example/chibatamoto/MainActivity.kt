package com.example.chibatamoto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chibatamoto.ui.theme.ChibatamotoTheme
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChibatamotoTheme {
                AppNavigation()
            }
        }
    }
}

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Products : Screen("products", "Products")
    object Consultations : Screen("consultations", "Consultations")
    object Seminars : Screen("seminars", "Seminars")
    object Diseases : Screen("diseases", "Diseases")
    object About : Screen("about", "About")
    object Testimonials : Screen("testimonials", "Testimonials")
    object Faqs : Screen("faqs", "FAQs")
    object DietaryTips : Screen("herbaltips", "Dietary Tips")
    object ContactUs : Screen("contactus", "Contact Us")
    object AgentOpportunities : Screen("agentopportunities", "Agent Opportunities")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            if (currentRoute != "seminarRegister") {
                TopAppBar(title = { Text(text = "Dr Chibatamoto Online Health Centre") })
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Products.route) { ProductsScreen(navController) }
            composable(Screen.Consultations.route) { ConsultationsScreen() }
            composable(Screen.Seminars.route) { SeminarsScreen(navController) }
            composable(Screen.Diseases.route) { DiseasesScreen(navController) }
            composable(Screen.About.route) { AboutScreen() }
            composable(Screen.Testimonials.route) { TestimonialsScreen(navController) }
            composable(Screen.Faqs.route) { FaqsScreen() }
            composable(Screen.DietaryTips.route) { DietaryTipsScreen(navController) }
            composable(Screen.ContactUs.route) { ContactUsScreen() }
            composable(Screen.AgentOpportunities.route) { AgentOpportunitiesScreen() }
            composable("productDetail/{productName}") { backStackEntry ->
                val productName = backStackEntry.arguments?.getString("productName") ?: ""
                ProductDetailScreen(productName, navController)
            }
            composable("seminarRegister") { SeminarRegisterScreen(navController) }
            composable("testimonialImage/{imageIndex}") { backStackEntry ->
                val imageIndex = backStackEntry.arguments?.getString("imageIndex")?.toIntOrNull() ?: 0
                TestimonialImageScreen(imageIndex, navController)
            }
        }
    }
}

@Composable
fun BackgroundedScreen(imageRes: Int, content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.25f),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    BackgroundedScreen(imageRes = R.drawable.herb_mint) {
        Column(modifier = Modifier.padding(32.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp)) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Button(onClick = { navController.navigate(Screen.Products.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("View Products")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.Consultations.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Request Consultation")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.Seminars.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Register for Seminars")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.Diseases.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Diseases We Treat")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.Testimonials.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Testimonials")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.Faqs.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("FAQs")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.DietaryTips.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Dietary Tips")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.ContactUs.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Contact Us")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.AgentOpportunities.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Agent Opportunities")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { navController.navigate(Screen.About.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("About the Clinic")
                    }
                }
            }
        }
    }
}

@Composable
fun ProductsScreen(navController: NavHostController) {
    val products = listOf(
        Product(
            "Aloemed",
            "It assists in:\n• Digestive problems\n• Arthritis\n• Treatment of skin conditions such as acne or psoriasis\n• Sunburn relief\n• Athritis\n• Type 2 Diabetes\n• Wound healing\n• Heartburn relief",
            0.0
        ),
        Product(
            "Suestmed",
            "It assists in the reversal of the following:\n• Wounds, Diabetes, gastritis, Bilharzia & bloody urine / hematuria, diarrhea, pneumonia, cancer, insect resistant\n• Cysts /fibroids /growths /lumps/tumors\n• Urinary tract disorders - blockage and colon cancer",
            0.0
        ),
        Product(
            "Expomed",
            "It assists in:\n• Relaxing muscles\n• Reducing inflammation\n• Relieving heart burn\n• Relieving acid reflux\n• Treating coughs, influenza, mastitis, backache, kidney disorders, hemorrhoids, and abdominal pains, scurvy, menstrual pain, hypertension, halitosis, gingivitis, cancer, and many others",
            0.0
        ),
        Product(
            "Wungmed",
            "It assists in treating the following:\n• Convulsions, Gonorrhea, syphilis & venereal diseases\n• Powdered over wounds, Powdered rubbed on gums & teeth for dental conditions, coughs & hoarseness of voice\n• Leprosy, Oral hygiene & pharyngeal ulcers, Anorexia & self-induced starvation, diarrhea & intestinal parasites\n• Edema, Diabetes, skin diseases, chronic fever, Spleen enlargement, Cancer",
            0.0
        ),
        Product(
            "CTM",
            "It assists in:\n• Regulation of Cycles\n• Reversing Stroke",
            0.0
        ),
        Product(
            "Sausmed",
            "It assists in treating the following:\n• Gargle for toothache\n• Treat epilepsy, skin ailments, wound healing and cleansing, boils, fungal infections, eczema, psoriasis, and ringworm\n• Internal ailments such as tape worm, dysentery, STIs, leprosy, diabetes, balancing hormones, backache, cancer, malaria, hemorrhaging, toothache, pneumonia, and used as a purgative",
            0.0
        ),
        Product(
            "Tambed",
            "It assists in treating the following:\n1. Abdominal Pain\n2. Gonorrhea\n3. Male Infertility and Prostate Problems\n4. Sore Throat\n5. It Also Has Antimalarial Activity",
            0.0
        ),
        Product(
            "Chenmed",
            "It assists in the following:\n1. lumps/growths\n2. Weak bladder\n3. Bile emesis\n4. Bilharzia\n5. Diarrhea\n6. Boils\n7. Abdominal pains\n8. Painful menstruation (menorrhagia)\n9. infertility in women\n10. Powder on WOUNDS\n11. Root powder under tongue for EDEMA.\n12. Infusion in mouth of infant for depressed fontanel (nhova)\n13. Powder as paste over snake bite area\n14. Antihelmintic and Antimicrobial Activities",
            0.0
        ),
        Product(
            "Ngurmed",
            "It assists in treating the following:\n• Cough\n• Penile Sores\n• Leprosy\n• Pneumonia\n• Powder Sniffed For Nose Bleeding\n• Bloody Diarrhea\n• Infertility In Women\n• Backache\n• Edema\n• Scorpion Bites\n• Epilepsy\n• Infant Abdominal Pains\n• Wound Healing\n• STIs\n• Sore Throat\n• Athritis",
            0.0
        ),
        Product(
            "Ziziemed",
            "It assists in the following ailments:\n1. Diaphoresis (sweating)\n2. Blood purification\n3. Root infusion for chest pains\n4. Dementia\n5. Sore throat\n6. Edema\n7. Panacea\n8. Abdominal pains\n9. Swollen stomach\n10. Diarrhea\n11. Eye drop for sore eyes\n12. STIs\n13. Teeth wash for sore tooth",
            0.0
        ),
        Product(
            "Ruguemed",
            "It assists in:\n• Antiviral and diuretic activities\n• Powder by mouth in porridge or under tongue for pneumonia\n• Abdominal pains\n• Gonorrhea\n• Infertility\n• Cough\n• Painful uterus\n• Chest pains\n• Powder in mouth for poisons\n• Powder under tongue for headache\n• General body malaise\n• Indigestion\n• Malaria & polio\n• Chest pains, arthritis & rheumatism\n• Yellow fever & constipation\n• Kidneys\n• Heart problems\n• Breast cancer\n• Powder in porridge for dyspnea (shortness of breath)",
            0.0
        ),
        Product(
            "Snackmed",
            "It assists in:\n1. Powder wrapped in clean cloth and dipping olive for 5 minutes then squeeze out the juice into the eyes with patient faced upwards for cataracts & eye problems\n2. Powder (tbsp.) + 1/2 tsp. Cayenne + 1 Raw Egg (Road Runner) + litre grape juice for three days = for sexual drive/strength and low sperm count\n3. It may also be good for: malaria, blood pressure, diabetes and toothache",
            0.0
        )
    )
    BackgroundedScreen(imageRes = R.drawable.herb_moringa) {
        Column(modifier = Modifier.padding(24.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(products) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { navController.navigate("productDetail/${product.name}") },
                        elevation = CardDefaults.cardElevation(4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = product.name.uppercase(),
                                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                            )
                            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
                            if (product.price > 0.0) {
                                Text(text = "Price: $${product.price}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "All products are developed under International Health and Life Consultancy, using locally sourced African medicinal plants and holistic formulation. Available in capsule and powder forms.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ProductDetailScreen(productName: String, navController: NavHostController) {
    val products = listOf(
        Product("Aloemed", "Digestive aid, diabetes, wound healing", 0.0),
        Product("Suestmed", "for Fibroids, tumors, wounds, bilharzia", 0.0),
        Product("Expomed", "for Acid reflux, heartburn, inflammation", 0.0),
        Product("Wungmed", "for Venereal diseases, coughs, diabetes, skin disorders", 0.0),
        Product("CTM", "for Stroke recovery, menstrual support", 0.0),
        Product("Sausmed", "Toothache, epilepsy, hormonal balance, ringworm", 0.0),
        Product("Tambed", "Male fertility, sore throat, STIs", 0.0),
        Product("Chenmed", "Painful menstruation, diarrhea, cysts", 0.0),
        Product("Ngurmed", "Cough, STIs, infertility, backache", 0.0),
        Product("Ziziemed", "Chest pain, dementia, sore eyes", 0.0),
        Product("Ruguemed", "Pneumonia, gonorrhea, malaria, prostate care", 0.0),
        Product("Snackmed", "Eye health, libido, universal detox", 0.0)
    )
    val product = products.find { it.name == productName } ?: return
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var showBuyDialog by remember { mutableStateOf(false) }
    var showEcocashDialog by remember { mutableStateOf(false) }
    var showBankDialog by remember { mutableStateOf(false) }
    val ussdCode = "*153*2*2*064251#"
    val bankName = "ZB Bank"
    val bankBranch = "Gweru"
    val bankAccount = "453700413496405"
    val bankHolder = "International Health and Life Consultancy"
    BackgroundedScreen(imageRes = R.drawable.herb_moringa) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Disclaimer:\nProducts are part of complementary medicine. Users must consult a certified health officer or Dr. Chibatamoto’s team before use, especially if pregnant, on medication, or nursing.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                val url = "https://wa.me/263712522763?text=I'm%20interested%20in%20${product.name}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Inquire Now (WhatsApp)")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { showBuyDialog = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Buy Now")
            }
            if (showBuyDialog) {
                AlertDialog(
                    onDismissRequest = { showBuyDialog = false },
                    title = { Text("Choose Payment Method") },
                    text = { Text("Select how you want to pay for this product.") },
                    confirmButton = {
                        Column {
                            TextButton(onClick = {
                                showBuyDialog = false
                                showEcocashDialog = true
                            }) {
                                Text("Pay with Ecocash")
                            }
                            TextButton(onClick = {
                                showBuyDialog = false
                                showBankDialog = true
                            }) {
                                Text("Bank Transfer")
                            }
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showBuyDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
            if (showEcocashDialog) {
                AlertDialog(
                    onDismissRequest = { showEcocashDialog = false },
                    title = { Text("Ecocash Payment Details") },
                    text = {
                        Column {
                            Text("Name: IHL Consultancy")
                            Text("USSD Payment Code:")
                            Text(ussdCode, style = MaterialTheme.typography.bodyMedium)
                        }
                    },
                    confirmButton = {
                        Column {
                            TextButton(onClick = {
                                clipboardManager.setText(AnnotatedString(ussdCode))
                            }) {
                                Text("Copy USSD Code")
                            }
                            TextButton(onClick = {
                                try {
                                    val encodedUssd = Uri.encode(ussdCode)
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$encodedUssd"))
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    // fallback: do nothing or show error
                                }
                            }) {
                                Text("Dial USSD Code")
                            }
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showEcocashDialog = false }) {
                            Text("Close")
                        }
                    }
                )
            }
            if (showBankDialog) {
                AlertDialog(
                    onDismissRequest = { showBankDialog = false },
                    title = { Text("Bank Transfer Details") },
                    text = {
                        Column {
                            Text("Bank: $bankName")
                            Text("Branch: $bankBranch")
                            Text("Account Name: $bankHolder")
                            Text("USD Account Number: $bankAccount")
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            clipboardManager.setText(AnnotatedString(bankAccount))
                        }) {
                            Text("Copy Account Number")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showBankDialog = false }) {
                            Text("Close")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Products")
            }
        }
    }
}

data class Product(val name: String, val description: String, val price: Double)

@Composable
fun ConsultationsScreen() {
    var name by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }
    BackgroundedScreen(imageRes = R.drawable.herb_aloe) {
        Column(modifier = Modifier.padding(32.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp)) {
                Column(modifier = Modifier.padding(24.dp)) {
                    if (submitted) {
                        Text("Consultation request submitted! We will contact you soon.")
                    } else {
                        Text("Request a Consultation", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = contact,
                            onValueChange = { contact = it },
                            label = { Text("Contact Info") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = reason,
                            onValueChange = { reason = it },
                            label = { Text("Reason for Consultation") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { submitted = true }, enabled = name.isNotBlank() && contact.isNotBlank() && reason.isNotBlank()) {
                            Text("Submit Request")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeminarsScreen(navController: NavHostController) {
    val seminars = listOf(
        "Basic Sign Language Training",
        "NCDs Prevention & Management",
        "Mental Health & Stress Management",
        "The Effects of Child Marriage",
        "Lifestyle & Diet Coaching",
        "African Natural Medicine Awareness",
        "Health Advocacy & Youth Leadership",
        "First Aid & Emergency Wellness",
        "Corporate/Staff Wellness Packages"
    )
    val audiences = listOf(
        "Schools & Colleges",
        "Churches",
        "Government Ministries",
        "Families or Community Groups",
        "Clinics & Health Facilities",
        "Private Individuals (Online or In-Person)",
        "Corporate Teams / Employees"
    )
    BackgroundedScreen(imageRes = R.drawable.herb_ginger) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text("This section allows you to enroll in ongoing or upcoming health, education, and empowerment courses hosted by Dr. Chibatamoto and the IHLC team.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text("\u2705 Available Programs Include:", style = MaterialTheme.typography.bodyMedium)
            seminars.forEach { Text("• $it", style = MaterialTheme.typography.bodySmall) }
            Spacer(modifier = Modifier.height(12.dp))
            Text("\uD83D\uDCDD These can be delivered to:", style = MaterialTheme.typography.bodyMedium)
            audiences.forEach { Text("• $it", style = MaterialTheme.typography.bodySmall) }
            Spacer(modifier = Modifier.height(12.dp))
            Text("\uD83D\uDCDD Registration Form Fields:", style = MaterialTheme.typography.bodyMedium)
            Text("• Full Name\n• Phone / WhatsApp\n• Email\n• Program of Interest (dropdown or open field)\n• Target Audience / Group (e.g., School, Church, Company, Personal)\n• Preferred Mode: Online / In-Person\n• Location (if in-person)", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("seminarRegister") }, modifier = Modifier.fillMaxWidth()) {
                Text("Register")
            }
        }
    }
}

@Composable
fun DiseasesScreen(navController: NavHostController) {
    val diseases = listOf(
        Disease(
            "Cancer(s)",
            "General wellness and integrative support.",
            "Symptoms: Vary by type; may include lumps, fatigue, weight loss, pain."
        ),
        Disease(
            "Heart Conditions",
            "Hypertension, stroke, circulation issues.",
            "Symptoms: High blood pressure, chest pain, dizziness, weakness."
        ),
        Disease(
            "Diabetes",
            "Type 1 & 2, blood sugar management.",
            "Symptoms: Increased thirst, frequent urination, fatigue, blurred vision."
        ),
        Disease(
            "Respiratory Diseases",
            "Asthma, allergies.",
            "Symptoms: Shortness of breath, wheezing, coughing, chest tightness."
        ),
        Disease(
            "Digestive Issues",
            "Acid reflux, constipation, ulcers.",
            "Symptoms: Heartburn, abdominal pain, bloating, irregular bowel movements."
        ),
        Disease(
            "Women’s Health",
            "Fibroids, hormonal imbalance, breast & cervical issues.",
            "Symptoms: Irregular periods, pelvic pain, hormonal symptoms."
        ),
        Disease(
            "Men’s Health",
            "Low libido, erectile dysfunction, prostate problems.",
            "Symptoms: Sexual dysfunction, urinary issues, low energy."
        ),
        Disease(
            "Joint and Bone Problems",
            "Arthritis, gout, back pain.",
            "Symptoms: Joint pain, swelling, stiffness, limited mobility."
        )
    )
    BackgroundedScreen(imageRes = R.drawable.herb_neem) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text("We provide one-on-one consultations (online and in-person), home visits, and health education tailored to help prevent, manage, or reverse the most common lifestyle and chronic diseases.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text("\u2705 Our Therapeutic Approach Includes:", style = MaterialTheme.typography.bodyMedium)
            Text("• Lifestyle Medicine\n• Diet and Nutrition Therapy\n• Hydrotherapy\n• African Natural Remedies (Complementary)\n• Mental and Emotional Well-being Interventions", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text("\uD83D\uDCA1 Key Health Areas Covered:", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(diseases) { disease ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = disease.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = disease.description, style = MaterialTheme.typography.bodyMedium)
                            Text(text = disease.symptoms, style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                Button(onClick = { navController.navigate(Screen.Consultations.route) }) {
                                    Text("Book Consultation")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(onClick = { navController.navigate(Screen.Products.route) }) {
                                    Text("Explore Products")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Disease(val name: String, val description: String, val symptoms: String)

@Composable
fun AgentOpportunitiesScreen() {
    BackgroundedScreen(imageRes = R.drawable.herb_moringa) {
        Column(modifier = Modifier.padding(32.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp)) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Agent Opportunities", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("""
Business Opportunity: Become a Lifestyle & Natural Medicine Agent

We offer free training, marketing support, and an internationally recognized license.

"No pyramid schemes, no recruiting—just health, business, and results."

For more information, contact us.
""".trimIndent(), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun AboutScreen() {
    val context = LocalContext.current
    BackgroundedScreen(imageRes = R.drawable.herb_mint) {
        Column(modifier = Modifier.padding(32.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp)) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("About Us", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("""
Dr. Chibatamoto Online Clinic App is a digital extension of the work and vision of Dr. Innocent Chibatamoto, a Lifestyle Medicine Specialist, Author, and Educator with a PhD in Natural Medicine and Health Sciences.

But this platform goes beyond just one name. This app represents a growing community of medical practitioners, researchers, wellness educators, and policy advocates working together under the banner of International Health and Life Consultancy (IHLC) — committed to building healthy, informed, and resilient societies across Southern Africa and beyond.

Our focus is on:
• Preventing and managing non-communicable diseases (NCDs)
• Promoting mental health and emotional wellness
• Integrating African traditional medicine with evidence-based modern care
• Educating individuals, families, institutions, and communities for lifestyle change

From digital consultations and herbal therapies to public seminars, workplace training, and grassroots advocacy — this app is part of a long-term movement toward accessible, affordable, and Africa-rooted healthcare.
""".trimIndent(), style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Stay connected with us online:", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "• Facebook: facebook.com/ihlconsultancy",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF1877F3),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/ihlconsultancy"))
                            context.startActivity(intent)
                        }
                    )
                    Text(
                        text = "• LinkedIn: linkedin.com/in/innocentchibatamoto",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF0A66C2),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/innocentchibatamoto"))
                            context.startActivity(intent)
                        }
                    )
                    Text(
                        text = "• Email: drchibs@ihlconsultancy.com",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF1A73E8),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:drchibs@ihlconsultancy.com")
                            }
                            context.startActivity(intent)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Owner: Dr I Chibatamoto", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TestimonialImageScreen(imageIndex: Int, navController: NavHostController) {
    val testimonialImages = (1..25).map { "k$it" }
    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = imageIndex, pageCount = { testimonialImages.size })
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Testimonial Image") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize().padding(innerPadding)) { page ->
            val imageName = testimonialImages[page]
            val imageRes = context.resources.getIdentifier(imageName, "drawable", context.packageName)
            var scale by remember { mutableStateOf(1f) }
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(page) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(1f, 5f)
                            offsetX += pan.x
                            offsetY += pan.y
                        }
                    }
                    .pointerInput(page) {
                        detectTapGestures(
                            onDoubleTap = {
                                scale = 1f
                                offsetX = 0f
                                offsetY = 0f
                            }
                        )
                    }
            ) {
                if (imageRes != 0) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Testimonial image fullscreen",
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale,
                                translationX = offsetX,
                                translationY = offsetY
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun TestimonialsScreen(navController: NavHostController) {
    val testimonialImages = (1..25).map { "k$it" }
    val context = LocalContext.current
    BackgroundedScreen(imageRes = R.drawable.herb_moringa) {
        LazyColumn(modifier = Modifier.padding(24.dp)) {
            itemsIndexed(testimonialImages) { idx, imageName ->
                val imageRes = context.resources.getIdentifier(imageName, "drawable", context.packageName)
                if (imageRes != 0) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.5f)
                                .padding(8.dp)
                                .clickable { navController.navigate("testimonialImage/$idx") }
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = "Testimonial image",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FaqsScreen() {
    val faqs = listOf(
        "Q: Do I need a prescription for herbal products?\nA: No, but a consultation is recommended.",
        "Q: Are your products safe for children?\nA: Yes, but please consult with Dr. Chibatamoto first.",
        "Q: How do I book a seminar?\nA: Use the 'Register for Seminars' section in the app."
    )
    BackgroundedScreen(imageRes = R.drawable.herb_aloe) {
        LazyColumn(modifier = Modifier.padding(24.dp)) {
            items(faqs) { faq ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                    Text(text = faq, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun DietaryTipsScreen(navController: NavHostController) {
    val sections = listOf(
        "Take a moment to reflect. These thought-provoking prompts and lifestyle insights are designed to help you examine your health habits, body signals, and daily choices.\nSometimes, true healing begins with one honest question:\nAm I living in a way that protects my health, or one that silently breaks it down?\nExplore. Reflect. Consult. Transform.",
        "🥦 Nutrition & Eating Habits" to listOf(
            "Do you rely on processed foods for most of your meals? Do you know their long-term effects?",
            "How often do you consume fizzy drinks or sugary snacks?",
            "When last did you eat something fresh from your own garden or local wild fruits?",
            "Did you know your chewing speed and style can impact digestion and overall health?"
        ),
        "💧 Hydration & Digestion" to listOf(
            "How much water do you drink daily—and do you know the right way and time to take it?",
            "Do you often feel bloated or heavy after meals?",
            "Do your meals leave you sluggish or energized?"
        ),
        "🚶🏽‍♀ Physical Activity & Sleep" to listOf(
            "Are you moving your body regularly—even lightly?",
            "How many restful hours of sleep do you get each night?",
            "Do you wake up refreshed, or already tired?"
        ),
        "❤ Women’s Health" to listOf(
            "Did you know iron loss during menstruation is common in women? How are you replenishing it?",
            "What are your go-to natural sources of iron—beetroot, molasses, spinach?"
        ),
        "🛡 Immunity & Stress" to listOf(
            "Are you often tired, even after a full night’s sleep?",
            "When sick, do you rest and fast, or continue eating without adjusting your diet?",
            "How does stress show up in your body?",
            "Are fresh fruits and vegetables part of your daily meals? Which ones do you prefer?"
        ),
        "🕊 Fasting & Detox" to listOf(
            "Have you noticed your body feeling better after skipping meals during illness?",
            "Could you benefit from a simple, regular fasting practice to support healing?",
            "What everyday toxins—chemical, emotional, or dietary—might be slowing your healing?"
        ),
        "🧬 Disease Awareness & Knowledge" to listOf(
            "Do you know the early signs of lifestyle diseases like diabetes, hypertension, and acid reflux?",
            "Are you aware that fatigue, headaches, or constant thirst may be signs of chronic imbalance?",
            "Have you ever checked your blood pressure or sugar levels—especially if lifestyle factors put you at risk?",
            "Did you know many cancers, strokes, and heart diseases are linked to long-term stress and diet choices?",
            "Are you familiar with the difference between Type 1 and Type 2 diabetes?",
            "Have you explored how digestive problems like ulcers, constipation, or bloating could be lifestyle-related?",
            "Are you conscious of reproductive health challenges like fibroids, cysts, or prostate issues—and their links to diet and stress?",
            "Do you understand how unmanaged conditions can affect your kidneys, liver, heart, and even mental clarity over time?"
        ),
        "💬 Call to Action" to listOf(
            "These aren’t just health facts—they're life-saving reflections.",
            "🌱 If any of these questions resonate with you, it may be time to act. Tap the “Request Consultation” button and let our team walk with you toward better health—one wise step at a time."
        )
    )
    BackgroundedScreen(imageRes = R.drawable.herb_ginger) {
        LazyColumn(modifier = Modifier.padding(24.dp)) {
            item {
                Text(
                    text = sections[0] as String,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            for (i in 1 until sections.size) {
                val (header, tips) = sections[i] as Pair<String, List<String>>
                item {
                    Text(header, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(vertical = 8.dp))
                }
                items(tips) { tip ->
                    Text("• $tip", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { navController.navigate(Screen.Consultations.route) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Request Consultation")
                }
            }
        }
    }
}

@Composable
fun ContactUsScreen() {
    BackgroundedScreen(imageRes = R.drawable.herb_neem) {
        Column(modifier = Modifier.padding(32.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp)) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Contact Us", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    val context = LocalContext.current
                    Text(
                        text = "Phone: +263 712 522 763",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF1A73E8),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+263712522763"))
                            context.startActivity(intent)
                        }
                    )
                    Text(
                        text = "WhatsApp: +263 712 522 763",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF25D366),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            val url = "https://wa.me/263712522763"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    )
                    Text(
                        text = "Email: admin@ihlconsultancy@gmail.com",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF1A73E8),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:admin@ihlconsultancy@gmail.com")
                            }
                            context.startActivity(intent)
                        }
                    )
                    Text("Address: Office 107 First Floor Dublin House Corner Albion and Mbuya Nehanda, Harare, Zimbabwe", style = MaterialTheme.typography.bodyMedium)
                    Text("Website: www.ihlconsultancy.com", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Or request a callback:", style = MaterialTheme.typography.bodySmall)
                    var name by remember { mutableStateOf("") }
                    var phone by remember { mutableStateOf("") }
                    var submitted by remember { mutableStateOf(false) }
                    if (submitted) {
                        Text("Thank you! We will contact you soon.", style = MaterialTheme.typography.bodyMedium)
                    } else {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Your Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("Your Phone Number") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = {
                                val message = "Callback Request:%0AName: $name%0APhone: $phone"
                                val url = "https://wa.me/263712522763?text=$message"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                                submitted = true
                            },
                            enabled = name.isNotBlank() && phone.isNotBlank()
                        ) {
                            Text("Request Callback")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeminarRegisterScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var program by remember { mutableStateOf("") }
    var audience by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var registered by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seminar Registration") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(24.dp).verticalScroll(scrollState)) {
            if (registered) {
                Text("Registration submitted! Our team will reach out to finalize the booking or schedule a planning discussion.")
            } else {
                Text("Register for a Program", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Phone / WhatsApp") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = program,
                    onValueChange = { program = it },
                    label = { Text("Program of Interest") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = audience,
                    onValueChange = { audience = it },
                    label = { Text("Target Audience / Group") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = mode,
                    onValueChange = { mode = it },
                    label = { Text("Preferred Mode: Online / In-Person") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location (if in-person)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val message = "Seminar Registration:%0A" +
                            "Full Name: $name%0A" +
                            "Phone / WhatsApp: $contact%0A" +
                            "Email: $email%0A" +
                            "Program of Interest: $program%0A" +
                            "Target Audience / Group: $audience%0A" +
                            "Preferred Mode: $mode%0A" +
                            "Location: $location"
                        val url = "https://wa.me/263712522763?text=$message"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                        registered = true
                    },
                    enabled = name.isNotBlank() && contact.isNotBlank() && email.isNotBlank() && program.isNotBlank() && audience.isNotBlank() && mode.isNotBlank()
                ) {
                    Text("Submit Registration")
                }
            }
        }
    }
}