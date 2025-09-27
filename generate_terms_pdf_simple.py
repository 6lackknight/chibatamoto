#!/usr/bin/env python3
"""
Simple script to convert Terms of Service to PDF using reportlab
"""

from reportlab.lib.pagesizes import letter, A4
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from reportlab.lib.colors import HexColor
from datetime import datetime
import os

def create_terms_pdf():
    """Create PDF from Terms of Service content"""
    
    # PDF filename
    pdf_filename = "Terms_of_Service_Dr_Chibatamoto.pdf"
    
    # Create the PDF document
    doc = SimpleDocTemplate(pdf_filename, pagesize=A4,
                          rightMargin=72, leftMargin=72,
                          topMargin=72, bottomMargin=72)
    
    # Get styles
    styles = getSampleStyleSheet()
    
    # Create custom styles
    title_style = ParagraphStyle(
        'CustomTitle',
        parent=styles['Heading1'],
        fontSize=18,
        spaceAfter=30,
        textColor=HexColor('#2c3e50'),
        alignment=1  # Center alignment
    )
    
    heading_style = ParagraphStyle(
        'CustomHeading',
        parent=styles['Heading2'],
        fontSize=14,
        spaceAfter=12,
        spaceBefore=20,
        textColor=HexColor('#34495e'),
        leftIndent=20
    )
    
    subheading_style = ParagraphStyle(
        'CustomSubHeading',
        parent=styles['Heading3'],
        fontSize=12,
        spaceAfter=8,
        spaceBefore=15,
        textColor=HexColor('#2c3e50')
    )
    
    normal_style = ParagraphStyle(
        'CustomNormal',
        parent=styles['Normal'],
        fontSize=10,
        spaceAfter=6,
        leading=14
    )
    
    # Content for Terms of Service
    current_date = datetime.now().strftime("%B %d, %Y")
    
    story = []
    
    # Title
    story.append(Paragraph("Terms of Service for Dr Chibatamoto App", title_style))
    story.append(Spacer(1, 20))
    
    # Effective Date
    story.append(Paragraph(f"<b>Effective Date:</b> {current_date}", normal_style))
    story.append(Paragraph(f"<b>Last Updated:</b> {current_date}", normal_style))
    story.append(Spacer(1, 20))
    
    # 1. Acceptance of Terms
    story.append(Paragraph("1. Acceptance of Terms", heading_style))
    story.append(Paragraph("By downloading, installing, or using the Dr Chibatamoto mobile application (\"App\"), you agree to be bound by these Terms of Service (\"Terms\"). If you do not agree to these Terms, do not use the App.", normal_style))
    story.append(Spacer(1, 12))
    
    # 2. Description of Service
    story.append(Paragraph("2. Description of Service", heading_style))
    story.append(Paragraph("The Dr Chibatamoto App provides:", normal_style))
    story.append(Paragraph("• Information about natural health products and herbal medicines", normal_style))
    story.append(Paragraph("• Health consultation services and educational content", normal_style))
    story.append(Paragraph("• Seminar registration and health education programs", normal_style))
    story.append(Paragraph("• Contact and communication services", normal_style))
    story.append(Paragraph("• Payment processing for products and services", normal_style))
    story.append(Spacer(1, 12))
    
    # 3. Medical Disclaimer
    story.append(Paragraph("3. Medical Disclaimer", heading_style))
    
    story.append(Paragraph("3.1 Not Medical Advice", subheading_style))
    story.append(Paragraph("The information provided in this App is for educational and informational purposes only. It is not intended as medical advice, diagnosis, or treatment. Always consult with a qualified healthcare professional before using any products or following any health recommendations.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("3.2 Complementary Medicine", subheading_style))
    story.append(Paragraph("Our products are part of complementary medicine and should not replace conventional medical treatment. Users must consult certified health professionals or Dr. Chibatamoto's team before use, especially if pregnant, on medication, or nursing.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("3.3 No Guarantees", subheading_style))
    story.append(Paragraph("We do not guarantee the effectiveness of any products or services. Individual results may vary, and we make no claims about curing or treating specific medical conditions.", normal_style))
    story.append(Spacer(1, 12))
    
    # 4. User Responsibilities
    story.append(Paragraph("4. User Responsibilities", heading_style))
    
    story.append(Paragraph("4.1 Accurate Information", subheading_style))
    story.append(Paragraph("You agree to provide accurate, current, and complete information when using our services, including contact forms and registration processes.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("4.2 Appropriate Use", subheading_style))
    story.append(Paragraph("You agree to use the App only for lawful purposes and in accordance with these Terms. You agree not to:", normal_style))
    story.append(Paragraph("• Use the App for any illegal or unauthorized purpose", normal_style))
    story.append(Paragraph("• Interfere with or disrupt the App's functionality", normal_style))
    story.append(Paragraph("• Attempt to gain unauthorized access to our systems", normal_style))
    story.append(Paragraph("• Share false or misleading information", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("4.3 Health Information", subheading_style))
    story.append(Paragraph("When sharing health-related information, you acknowledge that:", normal_style))
    story.append(Paragraph("• This information is shared voluntarily", normal_style))
    story.append(Paragraph("• We are not responsible for the accuracy of information you provide", normal_style))
    story.append(Paragraph("• You should consult healthcare professionals for medical advice", normal_style))
    story.append(Spacer(1, 12))
    
    # 5. Payment and Transactions
    story.append(Paragraph("5. Payment and Transactions", heading_style))
    
    story.append(Paragraph("5.1 Payment Methods", subheading_style))
    story.append(Paragraph("We accept payments through:", normal_style))
    story.append(Paragraph("• Ecocash (USSD Code: *153*2*2*064251#)", normal_style))
    story.append(Paragraph("• Bank Transfer (ZB Bank, Account: 453700413496405)", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("5.2 Payment Terms", subheading_style))
    story.append(Paragraph("• All prices are subject to change without notice", normal_style))
    story.append(Paragraph("• Payment must be completed before product delivery", normal_style))
    story.append(Paragraph("• We are not responsible for payment processing issues with third-party services", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("5.3 Refunds and Returns", subheading_style))
    story.append(Paragraph("• Refund policies are subject to individual product terms", normal_style))
    story.append(Paragraph("• Contact us directly for refund requests", normal_style))
    story.append(Paragraph("• We reserve the right to deny refunds in certain circumstances", normal_style))
    story.append(Spacer(1, 12))
    
    # 6. Intellectual Property
    story.append(Paragraph("6. Intellectual Property", heading_style))
    
    story.append(Paragraph("6.1 Our Content", subheading_style))
    story.append(Paragraph("All content in the App, including text, images, graphics, and software, is owned by Dr Chibatamoto and International Health and Life Consultancy and is protected by copyright laws.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("6.2 User Content", subheading_style))
    story.append(Paragraph("By submitting content to our App, you grant us a non-exclusive, royalty-free license to use, reproduce, and distribute such content for the purpose of providing our services.", normal_style))
    story.append(Spacer(1, 12))
    
    # 7. Privacy and Data Protection
    story.append(Paragraph("7. Privacy and Data Protection", heading_style))
    story.append(Paragraph("Your privacy is important to us. Please review our Privacy Policy, which is incorporated into these Terms by reference.", normal_style))
    story.append(Spacer(1, 12))
    
    # 8. Third-Party Services
    story.append(Paragraph("8. Third-Party Services", heading_style))
    
    story.append(Paragraph("8.1 WhatsApp Integration", subheading_style))
    story.append(Paragraph("Our App integrates with WhatsApp for communication. Your use of WhatsApp is subject to WhatsApp's own terms of service and privacy policy.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("8.2 Payment Processors", subheading_style))
    story.append(Paragraph("Payment processing is handled by third-party services (Ecocash, banking institutions). We are not responsible for their services or policies.", normal_style))
    story.append(Spacer(1, 12))
    
    # 9. Limitation of Liability
    story.append(Paragraph("9. Limitation of Liability", heading_style))
    
    story.append(Paragraph("9.1 Disclaimer of Warranties", subheading_style))
    story.append(Paragraph("THE APP IS PROVIDED \"AS IS\" WITHOUT WARRANTIES OF ANY KIND, EXPRESS OR IMPLIED.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("9.2 Limitation of Damages", subheading_style))
    story.append(Paragraph("IN NO EVENT SHALL DR CHIBATAMOTO BE LIABLE FOR ANY INDIRECT, INCIDENTAL, SPECIAL, CONSEQUENTIAL, OR PUNITIVE DAMAGES ARISING FROM YOUR USE OF THE APP.", normal_style))
    story.append(Spacer(1, 12))
    
    # 10. Indemnification
    story.append(Paragraph("10. Indemnification", heading_style))
    story.append(Paragraph("You agree to indemnify and hold harmless Dr Chibatamoto and International Health and Life Consultancy from any claims, damages, or expenses arising from your use of the App or violation of these Terms.", normal_style))
    story.append(Spacer(1, 12))
    
    # 11. Termination
    story.append(Paragraph("11. Termination", heading_style))
    
    story.append(Paragraph("11.1 Termination by You", subheading_style))
    story.append(Paragraph("You may stop using the App at any time.", normal_style))
    story.append(Spacer(1, 8))
    
    story.append(Paragraph("11.2 Termination by Us", subheading_style))
    story.append(Paragraph("We may terminate or suspend your access to the App at any time, with or without cause, with or without notice.", normal_style))
    story.append(Spacer(1, 12))
    
    # 12. Governing Law
    story.append(Paragraph("12. Governing Law", heading_style))
    story.append(Paragraph("These Terms shall be governed by and construed in accordance with the laws of Zimbabwe, without regard to conflict of law principles.", normal_style))
    story.append(Spacer(1, 12))
    
    # 13. Changes to Terms
    story.append(Paragraph("13. Changes to Terms", heading_style))
    story.append(Paragraph("We reserve the right to modify these Terms at any time. We will notify users of significant changes by updating the \"Last Updated\" date. Your continued use of the App after changes constitutes acceptance of the new Terms.", normal_style))
    story.append(Spacer(1, 12))
    
    # 14. Contact Information
    story.append(Paragraph("14. Contact Information", heading_style))
    story.append(Paragraph("For questions about these Terms of Service, please contact us:", normal_style))
    story.append(Spacer(1, 8))
    
    contact_style = ParagraphStyle(
        'ContactInfo',
        parent=normal_style,
        leftIndent=20,
        spaceAfter=4
    )
    
    story.append(Paragraph("<b>Dr Chibatamoto / International Health and Life Consultancy</b>", contact_style))
    story.append(Paragraph("• Phone: +263 712 522 763", contact_style))
    story.append(Paragraph("• WhatsApp: +263 712 522 763", contact_style))
    story.append(Paragraph("• Email: drchibs@ihlconsultancy.com", contact_style))
    story.append(Spacer(1, 12))
    
    # 15. Severability
    story.append(Paragraph("15. Severability", heading_style))
    story.append(Paragraph("If any provision of these Terms is found to be unenforceable, the remaining provisions will continue to be effective.", normal_style))
    story.append(Spacer(1, 20))
    
    # Footer
    footer_style = ParagraphStyle(
        'Footer',
        parent=normal_style,
        fontSize=8,
        textColor=HexColor('#666666'),
        alignment=1
    )
    
    story.append(Paragraph("Generated on: " + current_date, footer_style))
    story.append(Paragraph("Dr Chibatamoto / International Health and Life Consultancy", footer_style))
    
    # Build the PDF
    doc.build(story)
    print(f"✅ Terms of Service PDF created successfully: {pdf_filename}")

if __name__ == "__main__":
    try:
        create_terms_pdf()
    except Exception as e:
        print(f"Error generating PDF: {e}")
        print("Make sure you have reportlab installed: pip install reportlab") 