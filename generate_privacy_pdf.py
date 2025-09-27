#!/usr/bin/env python3
"""
Script to convert Privacy Policy to PDF using reportlab
"""

from reportlab.lib.pagesizes import letter, A4
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from reportlab.lib.colors import HexColor
from datetime import datetime
import os

def create_privacy_pdf():
    """Create PDF from Privacy Policy content"""
    
    # PDF filename
    pdf_filename = "Privacy_Policy_Dr_Chibatamoto.pdf"
    
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
        alignment=1,  # Center alignment
        textColor=HexColor('#2E7D32')  # Green color
    )
    
    heading_style = ParagraphStyle(
        'CustomHeading',
        parent=styles['Heading2'],
        fontSize=14,
        spaceAfter=12,
        spaceBefore=20,
        textColor=HexColor('#1B5E20')  # Darker green
    )
    
    body_style = ParagraphStyle(
        'CustomBody',
        parent=styles['Normal'],
        fontSize=11,
        spaceAfter=8,
        leading=14
    )
    
    # Content for Privacy Policy
    content = [
        Paragraph("Privacy Policy", title_style),
        Spacer(1, 20),
        
        Paragraph("Dr. Chibatamoto Online Health Centre", heading_style),
        Paragraph("Effective Date: " + datetime.now().strftime("%B %d, %Y"), body_style),
        Spacer(1, 20),
        
        Paragraph("1. Introduction", heading_style),
        Paragraph("This Privacy Policy describes how Dr. Chibatamoto Online Health Centre ('we', 'our', or 'us') collects, uses, and protects your information when you use our mobile application and related services. We are committed to protecting your privacy and ensuring the security of your personal information.", body_style),
        Spacer(1, 12),
        
        Paragraph("2. Information We Collect", heading_style),
        Paragraph("We may collect the following types of information:", body_style),
        Paragraph("• Personal Information: Name, phone number, email address, and health-related information you provide when using our consultation services, seminar registration, or contact forms.", body_style),
        Paragraph("• Usage Information: How you interact with our app, including features used and navigation patterns.", body_style),
        Paragraph("• Device Information: Device type, operating system, and app version for technical support and optimization.", body_style),
        Spacer(1, 12),
        
        Paragraph("3. How We Use Your Information", heading_style),
        Paragraph("We use the collected information for:", body_style),
        Paragraph("• Providing health consultation services and responding to your inquiries", body_style),
        Paragraph("• Processing seminar registrations and course enrollments", body_style),
        Paragraph("• Sending important updates about our services and health education content", body_style),
        Paragraph("• Improving our app functionality and user experience", body_style),
        Paragraph("• Complying with legal obligations and protecting our rights", body_style),
        Spacer(1, 12),
        
        Paragraph("4. Information Sharing", heading_style),
        Paragraph("We do not sell, trade, or rent your personal information to third parties. We may share your information only in the following circumstances:", body_style),
        Paragraph("• With your explicit consent", body_style),
        Paragraph("• To comply with legal requirements or court orders", body_style),
        Paragraph("• To protect our rights, property, or safety", body_style),
        Paragraph("• With trusted service providers who assist in app operations (under strict confidentiality agreements)", body_style),
        Spacer(1, 12),
        
        Paragraph("5. Data Security", heading_style),
        Paragraph("We implement appropriate technical and organizational measures to protect your personal information against unauthorized access, alteration, disclosure, or destruction. However, no method of transmission over the internet or electronic storage is 100% secure.", body_style),
        Spacer(1, 12),
        
        Paragraph("6. Third-Party Services", heading_style),
        Paragraph("Our app may integrate with third-party services such as WhatsApp for communication and payment gateways. These services have their own privacy policies, and we encourage you to review them.", body_style),
        Spacer(1, 12),
        
        Paragraph("7. Your Rights", heading_style),
        Paragraph("You have the right to:", body_style),
        Paragraph("• Access your personal information we hold", body_style),
        Paragraph("• Request correction of inaccurate information", body_style),
        Paragraph("• Request deletion of your personal information", body_style),
        Paragraph("• Withdraw consent for data processing", body_style),
        Paragraph("• Lodge a complaint with relevant authorities", body_style),
        Spacer(1, 12),
        
        Paragraph("8. Data Retention", heading_style),
        Paragraph("We retain your personal information only as long as necessary to provide our services and comply with legal obligations. Health-related information may be retained longer for medical record purposes.", body_style),
        Spacer(1, 12),
        
        Paragraph("9. Children's Privacy", heading_style),
        Paragraph("Our services are not intended for children under 13 years of age. We do not knowingly collect personal information from children under 13. If you believe we have collected such information, please contact us immediately.", body_style),
        Spacer(1, 12),
        
        Paragraph("10. International Transfers", heading_style),
        Paragraph("Your information may be transferred to and processed in countries other than your own. We ensure appropriate safeguards are in place to protect your information during such transfers.", body_style),
        Spacer(1, 12),
        
        Paragraph("11. Changes to This Policy", heading_style),
        Paragraph("We may update this Privacy Policy from time to time. We will notify you of any material changes by posting the new policy in our app and updating the effective date.", body_style),
        Spacer(1, 12),
        
        Paragraph("12. Contact Information", heading_style),
        Paragraph("If you have any questions about this Privacy Policy or our data practices, please contact us:", body_style),
        Paragraph("• WhatsApp: +263 712 522 763", body_style),
        Paragraph("• Email: drchibs@ihlconsultancy.com", body_style),
        Paragraph("• Address: International Health and Life Consultancy, Gweru, Zimbabwe", body_style),
        Spacer(1, 20),
        
        Paragraph("By using our app, you acknowledge that you have read and understood this Privacy Policy and agree to its terms.", body_style),
        Spacer(1, 30),
        
        Paragraph("Dr. Chibatamoto Online Health Centre", body_style),
        Paragraph("International Health and Life Consultancy", body_style),
        Paragraph("Gweru, Zimbabwe", body_style)
    ]
    
    # Build the PDF
    doc.build(content)
    print(f"✅ Privacy Policy PDF created successfully: {pdf_filename}")

if __name__ == "__main__":
    try:
        create_privacy_pdf()
    except Exception as e:
        print(f"Error generating PDF: {e}")
        print("Make sure you have reportlab installed: pip install reportlab") 