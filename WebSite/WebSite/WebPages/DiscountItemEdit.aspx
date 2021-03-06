﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountItemEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountItemEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="DiscountItemsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.DiscountItem"
        DeleteMethod="DeleteDiscountItem" InsertMethod="InsertDiscountItem" SelectMethod="GetDiscountItem"
        TypeName="NFCShoppingWebSite.BLL.DiscountItemBL" 
        UpdateMethod="UpdateDiscountItem">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="-1" Name="id" QueryStringField="discountItemID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discountItem" Type="Object" />
            <asp:Parameter Name="origDiscountItem" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="DiscountsDataSource" runat="server" 
        SelectMethod="GetDiscounts" TypeName="NFCShoppingWebSite.BLL.DiscountBL">
    </asp:ObjectDataSource>
    <asp:Label ID="TitleLabel" runat="server" Text="Label" Font-Bold="True" 
        Font-Size="X-Large" ForeColor="Black"></asp:Label>
    <br />
    <br />
    <asp:Label ID="Label2" runat="server" Text="商品名称"></asp:Label>
    <div>
        <asp:TextBox ID="ProductNameTextBox" runat="server"></asp:TextBox>
        <asp:DropDownList ID="ProductsDropDownList" runat="server" DataTextField="productID" DataValueField="productID">
        </asp:DropDownList>
        <asp:Button ID="SearchButton" runat="server" Text="搜索" 
            onclick="SearchButton_Click" />
    </div>
    <br />
    <div>
        <asp:Label ID="Label5" runat="server" Text="添加到该优惠活动"></asp:Label>
        <div>
            <asp:DropDownList ID="DiscountsDropDownList" runat="server" 
                DataSourceID="DiscountsDataSource" DataTextField="description" 
                DataValueField="discountID" ondatabound="DiscountsDropDownList_DataBound">
            </asp:DropDownList>
        </div>
    </div>
    <br />
    <asp:Label ID="Label3" runat="server" Text="折扣"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountPercentTextBox" runat="server" Text='<%# Eval("discountPercent") %>'></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label4" runat="server" Text="描述信息"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountItemDescriptionTextBox" Text='<%# Eval("description") %>' runat="server" Height="140px" TextMode="MultiLine"
        Width="442px"></asp:TextBox>
    <br />
    <br />
    <asp:Button ID="SubmitButton" runat="server" Text="确定" 
        OnClick="SubmitButton_Click" Width="86px"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="CancelButton" runat="server" Text="取消" 
        OnClick="CancelButton_Click" Width="87px"/>
    <br />
    <br />
</asp:Content>
